class PostNotFoundException(message: String) : RuntimeException(message)

data class Post(
    val text: String,
    val authorName: String,
    val date: Int,
    //val attachment: Array<Attachment> = emptyArray(),
    val signerId: Int? = null,
    val id: Int,
)

data class Comment(
    val id: Int,
    val fromId: Int? = null,
    val date: Int,
    val text: String,
    val replyToUser: Int? = null,
    val replyToComment: Int? = null,
    val postId: Int,
)

object WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()

    private var nextId = 0
    private var nextCommentId = 0

    fun add(post: Post): Post {
        posts += post.copy(id = nextId)
        nextId += 1
        return posts.last()
    }

    fun createComment(comment: Comment): Comment {
        for (post in posts) {
            if (post.id == comment.postId) {
                comments += comment.copy(id = nextCommentId)
                nextCommentId += 1
                return comments.last()
            }
        }
        throw PostNotFoundException("PostNotFoundException")
    }
}