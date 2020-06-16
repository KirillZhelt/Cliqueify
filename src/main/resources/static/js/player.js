const tag = document.createElement('script');

tag.src = "https://www.youtube.com/iframe_api";
const firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

let player;
function onYouTubeIframeAPIReady() {
    player = new YT.Player('player', {
        height: '390',
        width: '640',
        events: {
            'onReady': onPlayerReady,
        }
    });
}

function onPlayerReady(event) {
    playVideoFrom(0);
    pauseVideo();
}

function seekTo(seconds) {
    player.seekTo(seconds, true);
}

function playVideo() {
    player.playVideo();
}

function playVideoFrom(seconds) {
    seekTo(seconds);
    playVideo();
}

function pauseVideoOn(seconds) {
    pauseVideo();
    seekTo(seconds);
}

function pauseVideo() {
    player.pauseVideo();
}

function loadVideo(videoId, seconds) {
    const playerDiv = document.getElementById("player");
    playerDiv.dataset.videoId = videoId;

    player.loadVideoById(videoId, seconds);
}