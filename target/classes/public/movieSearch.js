function loadMovieInfo() {
    let movieTitle = document.getElementById("movieTitle").value;
    document.getElementById("movieContainer").style.display = 'flex';

    const apiKey = "b7232f2";
    const baseUrl = "https://www.omdbapi.com/?apikey=" + apiKey + "&t=";
    const fullUrl = baseUrl + encodeURIComponent(movieTitle);

    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        if (this.status === 200 && this.responseText) {
            const movieData = JSON.parse(this.responseText);
            let movieTitleElement = document.getElementById('movieTitle');
            let movieInfoElement = document.getElementById('movieInfo');
            let moviePosterElement = document.getElementById('moviePoster');

            movieTitleElement.textContent = movieData.Title;
            moviePosterElement.src = movieData.Poster;
            movieInfoElement.innerHTML = '';

            for (let key in movieData) {
                if (key !== 'Title' && key !== 'Poster') {
                    movieInfoElement.innerHTML += `<strong>${key}:</strong> ${movieData[key]}<br>`;
                }
            }

        } else {
            alert('Error al encontrar la película');
        }
    };

    xhttp.open("GET", fullUrl);
    xhttp.send();
}


document.getElementById('movieForm').addEventListener('submit', function(event) {
    event.preventDefault();
    loadMovieInfo();
});


function handleEnterPress(event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        loadMovieInfo();
    }
}

document.getElementById("movieTitle").addEventListener('keypress', handleEnterPress);