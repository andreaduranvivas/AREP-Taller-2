
/**
 * Load movie information from the OMDB API based on the input movie title.
 *
 * @param None
 * @return None
 */
function loadMovieInfo() {
    let movieTitle = document.getElementById("movieTitle").value;
    const url = new URL(window.location.href);
    url.searchParams.set('title', encodeURIComponent(movieTitle));
    window.history.pushState({ path: url.href }, '', url.href);

    document.getElementById("movieContainer").style.display = 'flex';

    const apiKey = "b7232f2";
    const baseUrl = "https://www.omdbapi.com/?apikey=" + apiKey + "&t=";
    const fullUrl = baseUrl + encodeURIComponent(movieTitle);

    const xhttp = new XMLHttpRequest();

    /**
     * Event handler for when the XMLHttpRequest object loads.
     * Parses the response data and updates the movie title, poster, and info elements
     * on the webpage based on the movie data received.
     */
    xhttp.onload = function() {
        if (this.status === 200 && this.responseText) {
            const movieData = JSON.parse(this.responseText);
            let movieTitleElement = document.getElementById('movieTitle');
            let movieInfoElement = document.getElementById('movieInfo');
            let moviePosterElement = document.getElementById('moviePoster');

            if (movieData.Response !== "False") {
                movieTitleElement.textContent = movieData.Title;
                moviePosterElement.src = movieData.Poster;
                movieInfoElement.innerHTML = `
                            <h3>${movieData.Title}</h3>
                            <div class="plot">${movieData.Plot}</div>
                            <div class="row">
                                <div class="col-md-6">
                                    ${generateInfoColumns(movieData, 0)}
                                </div>
                                <div class="col-md-6">
                                    ${generateInfoColumns(movieData, 1)}
                                </div>
                            </div>
                            <div id="genres" class="genres">${formatGenres(movieData.Genre)}</div>
                        `;
            } else {
                moviePosterElement.src = 'img/notFound.png';
                movieInfoElement.innerHTML = `
                             <h3>${movieData.Title}</h3>
                             <div class="row">
                                 <div class="col-md-6">
                                     ${generateInfoColumns(movieData, 0)}
                                 </div>
                                 <div class="col-md-6">
                                     ${generateInfoColumns(movieData, 1)}
                                 </div>
                             </div>
                        `;
            }
        } else {
            alert('Error al encontrar la película');
        }
    };

    xhttp.open("GET", fullUrl);
    xhttp.send();
}

/**
 * Generates HTML columns with movie information.
 *
 * @param {Object} movieData - The object containing movie information
 * @param {number} columnNumber - The column number for the HTML generation
 * @return {string} The HTML string containing the columns info
 */
function generateInfoColumns(movieData, columnNumber) {
    let infoHtml = '';
    let keys = Object.keys(movieData).filter(key =>
        key !== 'Title' && key !== 'Poster' && key !== 'Plot' && key !== 'Genre'
    );

    let startIndex = columnNumber * (keys.length / 2);
    let endIndex = (columnNumber + 1) * (keys.length / 2);

    for (let i = startIndex; i < endIndex; i++) {
        let key = keys[i];
        let value = movieData[key];

        if (value !== undefined) {
           infoHtml += `<strong>${key}:</strong> ${value}<br>`;
        }

    }

    return infoHtml;
}

/**
 * Formats the given genres into HTML span elements with a specific class.
 *
 * @param {string} genres - The string of genres separated by commas
 * @return {string} The formatted HTML string of genre items
 */
function formatGenres(genres) {
    const genreArray = genres.split(',').map(genre => genre.trim());
    return genreArray.map(genre => `<span class="genre-item">${genre}</span>`).join(' ');
}



document.getElementById('movieForm').addEventListener('submit', function(event) {
    event.preventDefault();
    loadMovieInfo();
});


/**
 * Handles the key press event and prevents the default action if the key is 'Enter'.
 *
 * @param {object} event - The key press event object
 * @return {void}
 */
function handleEnterPress(event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        loadMovieInfo();
    }
}

document.getElementById("movieTitle").addEventListener('keypress', handleEnterPress);

module.exports = {
  loadMovieInfo
};