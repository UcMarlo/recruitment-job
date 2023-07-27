package com.recruitment.job.infrastructure.github

import com.github.tomakehurst.wiremock.WireMockServer
import com.recruitment.job.infrastructure.github.exception.UserNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import spock.lang.Shared
import spock.lang.Specification

import java.time.ZonedDateTime

import static com.github.tomakehurst.wiremock.client.WireMock.*
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = TestConfiguration
)
@ActiveProfiles("test")
class GithubUserRepositoryIntegrationSpec extends Specification {

    @Configuration
    @ComponentScan("com.recruitment.job.infrastructure.github")
    static class TestConfiguration {}

    static final def WIREMOCK_PORT = 9000

    @DynamicPropertySource
    static void registerGithubProperties(DynamicPropertyRegistry registry) {
        registry.add("github.api.url", () -> "http://localhost:$WIREMOCK_PORT")
    }

    @Shared
    WireMockServer wiremockServer

    def setupSpec() {
        wiremockServer = new WireMockServer(options().port(WIREMOCK_PORT))
        wiremockServer.start()
    }

    def cleanupSpec() {
        wiremockServer.stop()
    }

    @Autowired
    GithubUserRepository githubUserRepository


    def 'should successfully map response form github'() {
        given:
        def existingLogin = "octocat"

        and:
        configureFor("wiremock.host", WIREMOCK_PORT)

        and:
        wiremockServer.stubFor(get("/users/$existingLogin")
                .willReturn(
                        ok()
                        .withBody(GITHUB_OK_RESPONSE)
                        .withHeader("Content-Type", "application/json")
                )
        )

        when:
        def user = githubUserRepository.findUserByLogin(existingLogin)

        then:
        noExceptionThrown()

        with(user) {\
            id == 1
            login == "octocat"
            name == "monalisa octocat"
            type == "User"
            avatarUri == "https://github.com/images/error/octocat_happy.gif"
            createdAt == ZonedDateTime.parse("2008-01-14T04:33:35Z")
            followersCount == 20
            publicRepositoriesCount == 2

        }
    }

    def 'should throw UserNotFoundException when github returns HTTP 404'() {
        given:
        def login = "nonExistingUser"

        and:
        configureFor("wiremock.host", WIREMOCK_PORT)

        and:
        wiremockServer.stubFor(get("/users/$login")
                .willReturn(
                        notFound()
                                .withBody(GITHUB_NOT_FOUND_RESPONSE)
                                .withHeader("Content-Type", "application/json")
                )
        )

        when:
        githubUserRepository.findUserByLogin(login)

        then:
        thrown UserNotFoundException
    }

    static final def GITHUB_NOT_FOUND_RESPONSE = """
        {
          "message": "Not Found",
          "documentation_url": "https://docs.github.com/rest/users/users#get-a-user"
        }
    """

    static final def GITHUB_OK_RESPONSE = """
        {
          "login": "octocat",
          "id": 1,
          "node_id": "MDQ6VXNlcjE=",
          "avatar_url": "https://github.com/images/error/octocat_happy.gif",
          "gravatar_id": "",
          "url": "https://api.github.com/users/octocat",
          "html_url": "https://github.com/octocat",
          "followers_url": "https://api.github.com/users/octocat/followers",
          "following_url": "https://api.github.com/users/octocat/following{/other_user}",
          "gists_url": "https://api.github.com/users/octocat/gists{/gist_id}",
          "starred_url": "https://api.github.com/users/octocat/starred{/owner}{/repo}",
          "subscriptions_url": "https://api.github.com/users/octocat/subscriptions",
          "organizations_url": "https://api.github.com/users/octocat/orgs",
          "repos_url": "https://api.github.com/users/octocat/repos",
          "events_url": "https://api.github.com/users/octocat/events{/privacy}",
          "received_events_url": "https://api.github.com/users/octocat/received_events",
          "type": "User",
          "site_admin": false,
          "name": "monalisa octocat",
          "company": "GitHub",
          "blog": "https://github.com/blog",
          "location": "San Francisco",
          "email": "octocat@github.com",
          "hireable": false,
          "bio": "There once was...",
          "twitter_username": "monatheoctocat",
          "public_repos": 2,
          "public_gists": 1,
          "followers": 20,
          "following": 0,
          "created_at": "2008-01-14T04:33:35Z",
          "updated_at": "2008-01-14T04:33:35Z",
          "private_gists": 81,
          "total_private_repos": 100,
          "owned_private_repos": 100,
          "disk_usage": 10000,
          "collaborators": 8,
          "two_factor_authentication": true,
          "plan": {
            "name": "Medium",
            "space": 400,
            "private_repos": 20,
            "collaborators": 0
          }
        }
    """
}