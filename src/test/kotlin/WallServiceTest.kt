import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {
    private var post = Post(
        id = 0,
        text = "text",
        authorName = "name",
        date = 25
    )
    private var comment = Comment(
        id = 1,
        postId = 1,
        text = "text",
        date = 25
    )
    private var comment2 = Comment(
        id = 0,
        postId = 0,
        text = "text",
        date = 25
    )


    @Test(expected = PostNotFoundException::class)
    fun shouldThrow() {
        WallService.createComment(comment)
    }

    @Test
    fun correctId() {
        WallService.add(post)
        val newComment = WallService.createComment(comment2)
        assertTrue(newComment.postId == post.id)
    }
}