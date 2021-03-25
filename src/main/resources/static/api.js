function createNode(element)
{
    return document.createElement(element);
}

function append(parent, el)
{
    return parent.appendChild(el);
}

const div = document.getElementById('posts');

fetch("api/posts")
    .then((resp) => resp.json())
    .then(function(data) {
        let posts = data;
        return posts.map(function(post) {
            let divArticle = createNode('div');
                //span = createNode('span');
            divArticle.innerHTML = `${post.id} ${post.title}`;
            divArticle.classList.add("article");
            //append(li, span);
            append(div, divArticle);
        })
    })
