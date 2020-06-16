function copyToClipboard(s) {
    const el = document.createElement('textarea');
    el.value = s;
    el.setAttribute('readonly', '');
    el.style.position = 'absolute';
    el.style.left = '-9999px';
    document.body.appendChild(el);
    el.select();
    document.execCommand('copy');
    document.body.removeChild(el);
}

const elements = document.querySelectorAll('.copy-link-btn');
Array.from(elements).forEach(e => {
    e.addEventListener('click', () => {
        const url = window.location.host + '/rooms/link/' + roomLinkToken;
        copyToClipboard(url);
    });
});