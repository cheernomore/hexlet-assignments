@import exercise.dto.posts.PostsPage
@import exercise.model.Post
@import exercise.util.NamedRoutes
@import java.time.LocalDate
@param PostsPage page

@template.layout.page(
content = @`
    @if(page.getPosts().isEmpty())
        Будьте первым, кто добавит пост!
        <button type="button" class="btn btn-outline-primary">
            <a href="${NamedRoutes.buildPostPath()}" class="card-link">Добавить пост</a>
        </button>
    @else
        @if(page.getFlash() != null)
            <div class="alert alert-success" role="alert" id="flash-message">
                ${page.getFlash()}
            </div>
        @endif

        @for(Post post: page.getPosts())

            <div class="card" style="width: 18rem;">
                <div class="card-body">
                    <h5 class="card-title">${post.getName()}</h5>
                    <h6 class="card-subtitle mb-2 text-body-secondary">${String.valueOf(LocalDate.now())}</h6>
                    <p class="card-text">${post.getBody()}</p>
                    <a href="${NamedRoutes.postPath(post.getId())}" class="card-link">Показать пост</a>
                </div>
            </div>
        @endfor
        <button type="button" class="btn btn-outline-primary">
            <a href="${NamedRoutes.buildPostPath()}" class="card-link">Добавить пост</a>
        </button>
    @endif
    <script>
        setTimeout(function() {
            var flashMessage = document.getElementById('flash-message');
            if (flashMessage) {
                flashMessage.style.display = 'none';
            }
        }, 3000);
    </script>
`
)