@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
    content = @`
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif
        <form method="POST" action="/articles">
            <label>
                Title
                <input required type="text" name="title" value="${page.getTitle()}">
            </label>
            <label>
                Content
                <textarea required name="content" id="" cols="30" rows="10">${page.getContent()}</textarea>
            </label>
            <input type="submit" value="Send">
        </form>
    `
)