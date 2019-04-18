function onCouponsClicked() {
    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onCouponsResponse);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/coupons');
    xhr.send();
}

function onPoemsLoad() {

}

function onProfileLoad(user) {
    clearMessages();
    showContents(['profile-content', 'logout-content', 'poems-content']);
    const usernameSpanEl = document.getElementById('user-name');
    usernameSpanEl.textContent = user.name;

    const poemsContentDivEl = document.getElementById('poems-content');

    const xhr = new XMLHttpRequest();
    xhr.addEventListener('load', onPoemsLoad);
    xhr.addEventListener('error', onNetworkError);
    xhr.open('GET', 'protected/profile');
    xhr.send();
}
