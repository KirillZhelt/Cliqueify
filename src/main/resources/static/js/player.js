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
            'onStateChange': onPlayerStateChange,
        }
    });
}

function onPlayerStateChange(e) {
    console.log(e.data);

    if (e.data === YT.PlayerState.PLAYING) {
        if (firstTimePlaying) {
            firstTimePlaying = false;
            onVideoLoaded();
        }
    }
}

function onPlayerReady(event) {
    if (startVideoId !== '') {
        if (startState !== null) {
            let seconds = 0;
            if (startState.type === 'PAUSED') {
                seconds = startState.elapsedTimeWhenPaused;
            } else if (startState.type === 'PLAYING') {
                seconds = countCurrentElapsedTime(startState.startedToPlayTime, startState.elapsedTime);
            }

            loadVideo(startVideoId, seconds);
        }
    }
}

function onVideoLoaded() {
    if (startState.type === 'PAUSED') {
        pauseVideoOn(startState.elapsedTimeWhenPaused);
    } else if (startState.type === 'PLAYING') {
        playVideoFrom(countCurrentElapsedTime(startState.startedToPlayTime, startState.startedToPlayTime));
    }
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