@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(
    content = @`
        <article class="card">
            <h1>${page.getPost().getName()}</h1>
            <p>${page.getPost().getBody()}</p>
            <a href="${NamedRoutes.postsPath()}?index=1" class="card-link">Back to posts</a>
        </article>
    `
)