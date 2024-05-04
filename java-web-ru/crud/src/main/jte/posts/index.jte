@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
    content = @`
    <section>
        @for(Post post: page.getPosts())
            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${post.getName()}</h5>
                    <h6 class="card-subtitle mb-2 text-muted">${post.getName()}</h6>
                    <p class="card-text">${post.getBody()}</p>
                    <a href="${NamedRoutes.postPath(post.getId())}" class="card-link">Show post</a>
                </div>
            </div>
        @endfor



        <nav aria-label="...">
            <ul class="pagination">
                <li class="page-item ${page.getIndex() == 1 ? "disabled" : ""}">
                    <a class="page-link" href="/posts?page=${page.getIndex() - 1}" tabindex="-1" aria-disabled="true">Previous</a>
                </li>

                @for(int i = 1; i <= page.getTotalPages(); i++)
                    <li class="page-item"><a class="page-link ${page.getIndex() == i ? "active" : ""}" href="/posts?page=${i}">${i}</a></li>
                @endfor


                <li class="page-item ${page.getIndex() == 7 ? "disabled" : ""}">
                    <a class="page-link" href="/posts?page=${page.getIndex() + 1}">Next</a>
                </li>
            </ul>
        </nav>
    </section>
    ` 
)