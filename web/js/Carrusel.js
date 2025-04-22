/* 
 * Js para Carrusel de imagenes
 */
let index = 0;
let autoPlay = true;
showSlide(index);
autoAdvance();

function plusSlides(n) {
  autoPlay = false; // desactiva autoplay si el usuario navega manualmente
  showSlide(index += n);
}

function showSlide(n) {
  const slides = document.getElementsByClassName("slides");
  if (n >= slides.length) index = 0;
  if (n < 0) index = slides.length - 1;

  for (let i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";
  }
  slides[index].style.display = "block";
}

function autoAdvance() {
  if (autoPlay) {
    plusSlides(1);
  }
  setTimeout(autoAdvance, 3000);
}


