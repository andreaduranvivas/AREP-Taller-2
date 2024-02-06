const { loadMovieInfo } = require('../../main/resources/public/movieSearch');
const { JSDOM } = require('jsdom');

describe('loadMovieInfo', () => {
  let window;

  beforeEach(() => {
    const dom = new JSDOM('<!DOCTYPE html><html><head></head><body></body></html>');
    window = dom.window;
    global.document = window.document;
  });

  afterEach(() => {
    delete global.document;
  });

  const OriginalXMLHttpRequest = window.XMLHttpRequest;

  function MockXHR() {}
  MockXHR.prototype.open = jest.fn();
  MockXHR.prototype.send = jest.fn();
  MockXHR.prototype.readyState =  4;
  MockXHR.prototype.status =  200;
  MockXHR.prototype.responseText = JSON.stringify({
    Response: 'True',
    Title: 'Example Movie Title',
    Poster: 'http://example.com/poster.jpg',
    Plot: 'Example plot',
    Genre: 'Adventure,Action',
  });

  beforeEach(() => {
    window.XMLHttpRequest = MockXHR;
  });

  afterEach(() => {
    window.XMLHttpRequest = OriginalXMLHttpRequest;
  });

  it('should update the DOM with movie details', () => {
    document.getElementById = jest.fn().mockImplementation((id) => {
      if (id === 'movieTitle') {
        return { value: 'Example Movie Title' };
      } else if (id === 'movieContainer') {
        return { style: {} };
      } else if (id === 'movieInfo') {
        return { innerHTML: '' };
      } else if (id === 'moviePoster') {
        return { src: '' };
      }
    });

    loadMovieInfo();

    expect(document.getElementById).toHaveBeenCalledWith('movieTitle');
    expect(document.getElementById).toHaveBeenCalledWith('movieContainer');
    expect(document.getElementById).toHaveBeenCalledWith('movieInfo');
    expect(document.getElementById).toHaveBeenCalledWith('moviePoster');

    const movieContainer = document.getElementById('movieContainer');
    expect(movieContainer.style.display).toEqual('flex');
    const movieInfo = document.getElementById('movieInfo');
    expect(movieInfo.innerHTML).toContain('Example Movie Title');
    const moviePoster = document.getElementById('moviePoster');
    expect(moviePoster.src).toEqual('http://example.com/poster.jpg');
  });
});
