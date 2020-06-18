function enterRoom() {
    liveRoomClient.connect(() => {
        liveRoomClient.sendAction({ type: "ENTER" });
    }, receiveAction);
}

function leaveRoom() {
    liveRoomClient.sendAction({ type: "LEAVE" });
}

async function loadTitleForYoutubeVideo(videoId) {
    const response = await fetch(`https://www.googleapis.com/youtube/v3/videos?part=snippet&id=${videoId}&key=AIzaSyC5y5ZFISW78pApDha-pSycQbSYqXPyMc0`);
    if (response.ok) {
        return (await response.json()).items[0].snippet.title;
    }
}

function countCurrentElapsedTime(startedToPlay, elapsedTime) {
    const now = new Date();
    const secondsPassed = (new Date(now.toUTCString()) - Date.parse(startedToPlay)) / 1000;
    return elapsedTime + secondsPassed;
}

function receiveAction(action) {
    const actionsArea = document.getElementById('actions-area');
    actionsArea.textContent = actionsArea.textContent + action.username + ' ' + action.action.type + '\n';

    if (action.username !== username) {
        if (action.action.type === 'PLAY') {
            playVideoFrom(countCurrentElapsedTime(action.action.startedToPlayTime, action.action.elapsedTime));
        } else if (action.action.type === 'PAUSE') {
            pauseVideoOn(action.action.elapsedTimeWhenPaused);
        } else if (action.action.type === 'LOAD') {
            loadVideo(action.action.videoId, countCurrentElapsedTime(action.action.startedToPlayTime, 0));
        } else if (action.action.type === 'ADD_VIDEO') {
            addVideoToPlaylist(action.action.videoId);
        }
    }
}

function fillTwoDigits(num) {
    if (num < 10) {
        return '0' + num;
    }

    return num.toString();
}

function formatDateUTC(date) {
    date = new Date(date.toUTCString());

    let formatted = `${date.getFullYear()}-`;

    formatted += fillTwoDigits(date.getMonth() + 1);
    formatted += '-';
    formatted += fillTwoDigits(date.getDate());
    formatted += 'T';
    formatted += fillTwoDigits(date.getHours());
    formatted += ':';
    formatted += fillTwoDigits(date.getMinutes());
    formatted += ':';
    formatted += fillTwoDigits(date.getSeconds());

    return formatted;
}

function onPlayButtonClicked() {
    playVideo();

    const playerDiv = document.getElementById("player");
    const videoId = playerDiv.dataset.videoId;

    // seekTo(60, true);
    console.log('current: ' + player.getCurrentTime());

    liveRoomClient.sendAction({
        type: "PLAY",
        elapsedTime: player.getCurrentTime(),
        startedToPlayTime: formatDateUTC(new Date()),
        videoId: videoId,
    });
}

function onPauseButtonClicked() {
    pauseVideo();

    const playerDiv = document.getElementById("player");
    const videoId = playerDiv.dataset.videoId;

    liveRoomClient.sendAction({ type: "PAUSE", elapsedTimeWhenPaused: player.getCurrentTime(), videoId: videoId });
}

function onVideoClick(e) {
    const videoId = e.target.dataset.videoId;
    loadVideo(videoId);
    liveRoomClient.sendAction({ type: 'LOAD', videoId: videoId, startedToPlayTime: formatDateUTC(new Date()) });
}

function getYoutubeId(url){
    const regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/;
    const match = url.match(regExp);
    return (match && match[7].length === 11) ? match[7] : false;
}

function createPlaylistItem(videoId) {
    const playlistItem = document.createElement('a');
    playlistItem.className = 'video-item list-group-item list-group-item-action';
    playlistItem.dataset.videoId = videoId;
    playlistItem.textContent = videoId;
    playlistItem.addEventListener('click', onVideoClick);

    loadTitleForYoutubeVideo(videoId).then((result) => {
        playlistItem.textContent = result;
    });

    return playlistItem;
}

function onAddVideoButtonClicked() {
    const url = document.getElementById('video-url').value;
    const videoId = getYoutubeId(url);

    addVideoToPlaylist(videoId);

    liveRoomClient.sendAction({ type: 'ADD_VIDEO', videoId: videoId });
}

function addVideoToPlaylist(videoId) {
    const playlist = document.getElementById('playlist');
    playlist.append(createPlaylistItem(videoId));
}

const liveRoomClient = new LiveRoomClient({
    'username': username,
    'host': 'http://localhost:8080/live',
    'sendTo': '/app/live-room/' + roomId,
    'subscribeTo': '/topic/' + roomId,
});

window.onload = () => {
    enterRoom();

    Array.from(document.querySelectorAll(".video-item")).forEach((el) => {
        el.addEventListener('click', onVideoClick);

        loadTitleForYoutubeVideo(el.dataset.videoId).then((result) => {
            el.textContent = result;
        });
    });
}

window.onbeforeunload = () => {
    leaveRoom();
}