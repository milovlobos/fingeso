<template>
    <div class="container main">
        <header>
            <section class="header-section">
                <h1 class="page-title">InstaHome</h1>
                <div class="button-container1" v-if="logged">
                        <router-link to="/publish">
                            <div type="button" class="btn btn-secondary">
                                <i class="fi fi-rr-home"></i>Publicar
                            </div>
                        </router-link>
                        <div type="button" class="btn btn-secondary">
                            <i class="fi fi-rr-menu-burger"></i>Menu
                        </div>
                </div>
                <div class="button-container1" v-if="!logged">
                    <router-link to="/login">
                        <div type="button" class="btn btn-secondary">
                            <i class="fi fi-rr-user"></i>Ingreso
                        </div>
                    </router-link>
                        <div type="button" class="btn btn-secondary">
                            <i class="fi fi-rr-menu-burger"></i>Menu
                        </div>
                </div>
            </section>
        </header>

        <section>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Ubicacion
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" id="location1">RM</a></li>
                                    <li><a class="dropdown-item" id="location2">Valparaiso</a></li>
                                    <li><a class="dropdown-item" id="location3">Viña del mar</a></li>
                                    <li><a class="dropdown-item" id="location4">Rancagua</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Tipo de propiedad
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" id="location1">Casa</a></li>
                                    <li><a class="dropdown-item" id="location2">Terreno</a></li>
                                    <li><a class="dropdown-item" id="location3">Departamento</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Asociacion
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" id="location1">Viel.cl</a></li>
                                    <li><a class="dropdown-item" id="location2">Target</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <div class="priceFilter" data-role="rangeslider">
                                    <label for="price-min">Precio:</label>
                                    <input type="range" name="price-min" id="price-min" value="200" min="0" max="10000000000">
                                    <input type="number" id="price-display" value="200" min="0" max="10000000000">
                                </div>
                            </li>
                        </ul>
                        <form class="d-flex">
                            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                            <button class="btn btn-primary" type="submit">Search</button>
                        </form>
                    </div>
                </div>
            </nav>
        </section>
        
        <section id="properties">
            <h1 class="main-title">Propiedades Disponibles</h1>
            <div class="card-container">
                <div class="card">
                    <img src="./media/casa1.jpg" alt="Imagen de la propiedad">
                    <div class="card-content">
                        <h3 class="letter">Propiedad 1</h3>
                        <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                        <p class="letter">Valor:"Texto de ejemplo"</p>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" onclick="setModalContent('Propiedad 6', './media/casa6.jpg', 'Descripción de la propiedad: Texto de ejemplo', 'Valor: Texto de ejemplo')">Ver mas</button>
                    </div>
                </div>
                <div class="card">
                    <img src="./media/casa6.jpg" alt="Imagen de la propiedad">
                    <div class="card-content">
                        <h3 class="letter">Propiedad 2</h3>
                        <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                        <p class="letter">Valor:"Texto de ejemplo"</p>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" onclick="setModalContent('Propiedad 6', './media/casa6.jpg', 'Descripción de la propiedad: Texto de ejemplo', 'Valor: Texto de ejemplo')">Ver mas</button>
                    </div>
                </div>
                <div class="card">
                    <img src="./media/casa3.jpg" alt="Imagen de la propiedad">
                    <div class="card-content">
                        <h3 class="letter">Propiedad 3</h3>
                        <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                        <p class="letter">Valor:"Texto de ejemplo"</p>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" onclick="setModalContent('Propiedad 6', './media/casa6.jpg', 'Descripción de la propiedad: Texto de ejemplo', 'Valor: Texto de ejemplo')">Ver mas</button>
                    </div>
                </div>
            </div>
            <div class="card-container">
                <div class="card">
                    <img src="./media/casa3.jpg" alt="Imagen de la propiedad">
                    <div class="card-content">
                        <h3 class="letter">Propiedad 4</h3>
                        <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                        <p class="letter">Valor:"Texto de ejemplo"</p>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" onclick="setModalContent('Propiedad 6', './media/casa6.jpg', 'Descripción de la propiedad: Texto de ejemplo', 'Valor: Texto de ejemplo')">Ver mas</button>
                    </div>
                </div>
                <div class="card">
                    <img src="./media/casa5.jpg" alt="Imagen de la propiedad">
                    <div class="card-content">
                        <h3 class="letter">Propiedad 5</h3>
                        <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                        <p class="letter">Valor:"Texto de ejemplo"</p>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" onclick="setModalContent('Propiedad 6', './media/casa6.jpg', 'Descripción de la propiedad: Texto de ejemplo', 'Valor: Texto de ejemplo')">Ver mas</button>
                    </div>
                </div>
                <div class="card">
                    <img src="./media/casa6.jpg" alt="Imagen de la propiedad">
                    <div class="card-content">
                        <h3 class="letter">Propiedad 6</h3>
                        <p class="letter">Descripcion de la propiedad:"Texto de ejemplo"</p>
                        <p class="letter">Valor:"Texto de ejemplo"</p>
                        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#propertyModal" onclick="setModalContent('Propiedad 6', './media/casa6.jpg', 'Descripción de la propiedad: Texto de ejemplo', 'Valor: Texto de ejemplo')">Ver mas</button>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="propertyModal" tabindex="-1" aria-labelledby="propertyModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="propertyModalLabel">Detalles de la Propiedad</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <img id="modalImage" src="" alt="Imagen de la propiedad">
                            <h3 id="modalTitle"></h3>
                            <p id="modalDescription"></p>
                            <p id="modalValue"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                            <router-link to="/buy">
                                <button type="button" class="btn btn-primary">Comprar</button>
                            </router-link>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <nav class="nav-bar1"aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
        
        <footer class="footer1">
            <p>&copy; 2024 Portal de arriendo.Todos los derechos reservados</p>
            <p><a href="#contact" data-bs-toggle="modal" data-bs-target="#contactModal">Contactanos</a> para mas informacion</p>
                <div class="modal fade" id="contactModal" tabindex="-1" aria-labelledby="contactModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="contactModalLabel">Envíanos un Correo</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form id="contactForm">
                                    <div class="mb-3">
                                        <label for="name" class="form-label">Nombre</label>
                                        <input type="text" class="form-control" id="name" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Correo Electrónico</label>
                                        <input type="email" class="form-control" id="email" required>
                                    </div>
                                    <div class="mb-3">
                                        <label for="message" class="form-label">Mensaje</label>
                                        <textarea class="form-control" id="message" rows="3" required></textarea>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal">Cerrar</button>
                            <button type="submit" class="btn btn-primary" onclick="submitForm()">Enviar</button>
                        </div>
                    </div>
                </div>
            </div>
        </footer> 
    </div>
</template>

<script>

    function setModalContent(title, imageSrc, description, value){
        document.getElementById('modalTitle').innerText = title;
        document.getElementById('modalImage').src = imageSrc;
        document.getElementById('modalDescription').innerText = description;
        document.getElementById('modalValue').innerText = value;
    }

    export default{

        data(){

            return{

                logged:true,
                locationFilter: false,
            }
        },

        methods:{

            setLocation(){

                const item = document.getElementById(elementId);
                const texto = item.textContent || item.innerText;
                this.locationFilter = !this.locationFilter;
                dropdownToggle.textContent = texto;

            },

        }

    }


</script>



<style>

.main{

    background: linear-gradient(45deg, #ded1b6, #ded1b6, #6ca19e, #6d997a);
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    padding: 20px;
}

.header-section {
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.page-title {
    text-align: left;
    color: black;
    margin: 0;
}
.main-title{

    padding-top: 15px;
    color: black;
}
.letter{

    padding-top: 5px;
    color: rgb(0, 0, 0);
}

.card-container{
    display: flex;
    justify-content: space-between;
    margin: 20px;
}

.card{

    background-color: #f8f6f691;
    border: 1px solid #ccc;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
    margin: 20px;
    max-width: 450px;
    overflow: hidden;
    transition: transform 0.3s;

}

.card:hover{
    transform: scale(1.1);
}

.card img{

    border-radius: 8px 8px 0 0;
    max-width: 100%;
    height: auto;
}

.card-content{

    color: black;
    padding: 15px;
}

.card-content h3{

    font-size: 1.5em;
    margin: 0.5em 0;
}

.card-content button{

    background-color: #6d997a;
    border: none;
    border-radius: 4px;
    color: white;
    cursor: pointer;
    padding: 10px;
    width: 100%;
}

.card-content button:hover{

    background-color: #42282c;
}

.navbar {
    padding-top: 5px;
    padding-bottom: 5px;
}

.navbar-brand h1 {
    font-size: 1.5rem;
    margin: 0;
}

.navbar-nav .nav-item {
    margin-bottom: 0;
}

.form-control {
    height: 38px; /* Asegúrate de que la altura sea la misma para el input */
    padding: 5px 10px;
    font-size: 1rem;
    border-radius: 5px; /* Si quieres que los bordes sean redondeados, iguala este valor */
}

.button-container1 {
    text-align: right; /* Alinea los botones a la derecha */
    margin: 20px; /* Espaciado alrededor del contenedor */
}

.footer1{

    color: black;
    text-align: center;
    padding: 10px;
    border-top: 1px solid #ddd;
    width: 100%;
    position: relative;
    bottom: 0;
}
.priceFilter {
    padding-left: 3px;
    padding-top: 6px;
    color: rgba(0,0,0,.55);
    display: flex;
    align-items: center; /* Alinea verticalmente los elementos en el centro */
    gap: 10px; /* Espacio entre el texto y el slider */
}

.priceFilter label {
    margin: 0; /* Elimina el margen por defecto del label si es necesario */
}

.priceFilter input[type="range"] {
    flex: 1; /* Hace que el slider ocupe el espacio restante */
}

.nav-bar1{

    display: flex;
    justify-content: center; /* Centra horizontalmente el contenedor */
    padding: 20px; /* Espacio alrededor del contenedor (opcional) */
}

.pagination {
    display: flex;
    gap: 10px; /* Espacio entre los elementos de paginación */
    padding: 0;
    margin: 0;
    list-style: none; /* Elimina los puntos de lista */
}

.page-link {
    display: block;
    padding: 10px 15px; /* Espaciado interno del enlace */
    border: 1px solid rgba(0, 0, 0, 0.125); /* Borde translúcido */
    border-radius: 4px; /* Bordes redondeados */
    background-color: transparent; /* Fondo translúcido */
    color: #42282c; /* Color del texto */
    text-decoration: none; /* Quita el subrayado */
    transition: background-color 0.3s; /* Transición para el cambio de color */
}

.page-link:hover {
    background-color: rgba(0, 0, 0, 0.1); /* Fondo al pasar el ratón sobre el enlace */
}

.modal-title{

    color: black;
}

.modal-body{

    color:black;
}
</style>