const Footer = () => {
    return ( 
        <>
            <footer class="footer-i bg-menu">
        <div class="footer-des">
            <h3 class="footer-title"><span class="font-mod">el patio</span></h3>
            <h4 class="footer-text">- Restaurant de comida mejicana -</h4>
            <h4 class="footer-text">Bandera de Los Andes 1925</h4>
            <h4 class="footer-text">Ciudad de Mendoza</h4>
        </div>
        <div class="footer-section">
            <h3 class="footer-title">Navegación</h3>
            <div class="footer-links">
                <ul>
                    <li><a href="pages/carta.html">Carta</a></li>
                    <li><a href="#reservas">Reservas</a></li>
                    <li><a href="#ubicacion">Ubicación</a></li>
                    <li><a href="#reviews">Reseñas</a></li>
                    <li><a href="#nosotros">Nosotros</a></li>
                    <li><a href="pages/contacto.html">Contacto</a></li>
                </ul>
            </div>

        </div>
        <div class="footer-section">

            <h3 class="footer-title">Nuestras redes sociales</h3>
            <div>
                <ul class="footer-icons">
                    <li><a href="https://www.whatsapp.com/?lang=es" target="_blank"><i
                                class='bx bxl-whatsapp footer-icon'></i><span> Whatsapp</span></a></li>
                    <li><a href="https://www.instagram.com/" target="_blank"><i
                                class='bx bxl-instagram footer-icon'></i><span>Instagram</span></a></li>
                    <li><a href="https://github.com/PabloEchegaray97" target="_blank"><i
                                class='bx bxl-github footer-icon'></i><span>Github</span></a></li>
                    <li><a href="https://www.linkedin.com/in/pablo-echegaray-a4a000241/" target="_blank"><i
                                    class='bx bxl-linkedin footer-icon'></i><span>Linkedin</span></a></li>
                    
                </ul>
            </div>
        </div>
    </footer>
        </>
     );
}
 
export default Footer;