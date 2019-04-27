function showPoemContent(poem) {
    const pEl = document.createElement('p');
    pEl.innerHTML = poem.content;
    return pEl;
}

function onPoemContentLoad(poem) {
    const poemLiEl = document.getElementById(poem.id);
    const poemUlEl = document.getElementById('poems');
    debugger;
    for (let i = 0; i < poemUlEl.childNodes.length; i++) {
        if (poemUlEl.childNodes[i].childNodes.length > 1) {
            poemUlEl.childNodes[i].removeChild(poemUlEl.childNodes[i].lastChild);
        }
    }
    poemLiEl.appendChild(showPoemContent(poem));
}

function onPoemContentResponse() {
    if (this.status === OK) {
        showContents(['profile-content', 'poems-content', 'logout-content']);
        onPoemContentLoad(JSON.parse(this.responseText));
    } else {
        onOtherResponse(profileContentDivEl, this);
    }
}

function onPoemTitleClick() {
    const params = new URLSearchParams();
    const title = this.textContent;
    params.append('title', title);
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemContentResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('POST', 'protected/poem');
    xhr.send(params);
}

function createPoemsList(poems) {
    const ulEl = document.createElement('ul');
    ulEl.setAttribute('id', 'poems');

    for (let i = 0; i < poems.length; i++) {
      const poem = poems[i];

      const pEl = document.createElement('p');
      pEl.textContent = poem.title;
      pEl.addEventListener('click', onPoemTitleClick)

      const liEl = document.createElement('li');
      liEl.setAttribute('id', poem.id);
      liEl.appendChild(pEl);

      ulEl.appendChild(liEl);
    }

    return ulEl;
}

function onPoemsLoad(poems) {
    poemsContentDivEl = document.getElementById('poems-content');
    poemsContentDivEl.style.display = 'block';

    while (poemsContentDivEl.firstChild) {
      poemsContentDivEl.removeChild(poemsContentDivEl.firstChild);
    }
    poemsContentDivEl.appendChild(createPoemsList(poems));
}

function onPoemsResponse() {
    if (this.status === OK) {
        showContents(['profile-content', 'poems-content', 'logout-content']);
        onPoemsLoad(JSON.parse(this.responseText));
    } else {
        onOtherResponse(profileContentDivEl, this);
    }
}

function onProfileLoad(user) {
    clearMessages();
    showContents(['profile-content', 'logout-content']);
    const usernameSpanEl = document.getElementById('user-name');
    usernameSpanEl.textContent = user.name;

    const poemsContentDivEl = document.getElementById('poems-content');

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemsResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/profile');
    xhr.send();
}
