/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
class AppTest {
    @Autowired
    PostsRepository postsRepository;

   // @Test
    void appHasAGreeting() {
        /*
        App classUnderTest = new App();
        String title = "Test Title";
        String content = "Happy Day";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("itsme@gmail.com")
                .build());
        List<Posts> postList = postsRepository.findAll();

        Posts posts;
        posts = postList.get(0);
        assertEquals(posts.getTitle(), title);
        */
    }
}
