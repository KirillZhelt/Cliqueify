function enterRoom() {
    liveRoomClient.connect(() => {
        liveRoomClient.sendAction({ type: "ENTER" });
    }, receiveAction);
}

function leaveRoom() {
    liveRoomClient.sendAction({ type: "LEAVE" });
}

function countCurrentElapsedTime(startedToPlay, elapsedTime) {
    const secondsPassed = (new Date() - Date.parse(startedToPlay)) / 1000;
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
        }
    }
}

function fillTwoDigits(num) {
    if (num < 10) {
        return '0' + num;
    }

    return num.toString();
}

function formatDate(date) {
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

    liveRoomClient.sendAction({
        type: "PLAY",
        elapsedTime: player.getCurrentTime(),
        startedToPlayTime: formatDate(new Date()),
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
    liveRoomClient.sendAction({ type: 'LOAD', videoId: videoId, startedToPlayTime: formatDate(new Date()) });
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
    });
}

window.onbeforeunload = () => {
    leaveRoom();
}