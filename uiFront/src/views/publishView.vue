<template>

    <div class="container main">

        <header>
            <section class="header-section">
                <router-link to="/">
                    <h1 class="page-title">InstaHome</h1>
                </router-link>
            </section>
        </header>
        <div class="content">
            <div class="colum-content1">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a :class="{'nav-link': true, 'active': activeItem === 1}" @click="setActive(1)">Datos personales</a>
                    </li>
                    <li class="nav-item">
                        <a :class="{'nav-link': true, 'active': activeItem === 2}" @click="setActive(2)">Datos de la vivienda</a>
                    </li>
                    <li class="nav-item">
                        <a :class="{'nav-link': true, 'active': activeItem === 3}" @click="setActive(3)">Metodos de pago</a>
                    </li>
                    <li class="nav-item">
                        <a :class="{'nav-link': true, 'active': activeItem === 4}" @click="setActive(4)">Nivel de verificacion</a>
                    </li>
                </ul>
            </div>
            <form class="d-flex">
                <button class="btn btn-primary" @click.prevent="prevSection()" id="prevButton">Atras</button>
                <button class="btn btn-primary" @click.prevent="nextSection()" id="nextButton">Siguiente</button>
                </form>
        </div>

        <div class="content2" id="personal-form">
            <div class="progress">
                <div class="progress-bar":class="{ 'progress-bar-full': progressWidth === '100%' }"  role="progressbar" :style="{ width: progressWidth }" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
            <form class="row g-3"v-if="progress==25">
                <div class="col-md-6" >
                    <label for="inputusername4" class="form-label">Nombre Completo*</label>
                    <input type="username" class="form-control" id="inputusernamel4">
                </div>
                <div class="col-md-6">
                    <label for="inputRut4" class="form-label">Rut*</label>
                    <input type="text" class="form-control" id="inputRut4">
                </div>
                <div class="col-12">
                    <label for="inputAddress" class="form-label">Direccion*</label>
                    <input type="text" class="form-control" id="inputAddress" placeholder="Calle falsa #123">
                </div>
                <div class="col-12">
                    <label for="inputAddress2" class="form-label">Numero de telefono*</label>
                    <input type="text" class="form-control" id="inputAddress2" placeholder="+56 9 1234 56789">
                </div>
                <div class="col-md-6">
                    <label for="inputRegion" class="form-label">Region*</label>
                    <select id="inputRegion" class="form-select">
                        <option selected>...</option>
                        <option>Arica y parinacota</option>
                        <option>Tarapaca</option>
                        <option>Antofagasta</option>
                        <option>Atacama</option>
                        <option>Coquimbo</option>
                        <option>Valparaiso</option>
                        <option>O'Higgins</option>
                        <option>Maule</option>
                        <option>Ñuble</option>
                        <option>Araucania</option>
                        <option>Los rios</option>
                        <option>Los lagos</option>
                        <option>Aysen</option>
                        <option>Magallanes</option>
                        <option>Antartica</option>
                        <option>Isla de pascua</option>
                        <option>Metropolitana</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <label for="inputState" class="form-label">Comuna*</label>
                    <input type="text" class="form-control" id="inputCity">
                </div>
                <div class="col-md-2">
                    <label for="inputZip" class="form-label">Codigo Postal</label>
                    <input type="text" class="form-control" id="inputZip">
                </div>
                <div class="col-12">
            </div>
            </form>

            <form class="row g-3" v-if="progress==50">
                <div class="row">
                    <div class="col-md-6">
                        <label for="inputusername4" class="form-label">Tipo de propiedad*</label>
                        <select id="inputRegion" class="form-select">
                            <option selected>...</option>
                            <option>Departamento</option>
                            <option>Casa</option>
                            <option>Terreno</option>
                        </select>
                    </div>
                <div class="col-md-6">
                    <label for="inputRut4" class="form-label">Direccion*</label>
                    <input type="text" class="form-control" id="inputRut4">
                </div>
                <div class="col-12">
                    <label for="inputAddress" class="form-label">Fotos de la vivienda*</label>
                    <input type="file" class="form-control" id="inputAddress">
                </div>
                <div class="col-md-6">
                    <label for="inputRegion" class="form-label">Region*</label>
                    <select id="inputRegion" class="form-select">
                        <option selected>...</option>
                        <option>Arica y parinacota</option>
                        <option>Tarapaca</option>
                        <option>Antofagasta</option>
                        <option>Atacama</option>
                        <option>Coquimbo</option>
                        <option>Valparaiso</option>
                        <option>O'Higgins</option>
                        <option>Maule</option>
                        <option>Ñuble</option>
                        <option>Araucania</option>
                        <option>Los rios</option>
                        <option>Los lagos</option>
                        <option>Aysen</option>
                        <option>Magallanes</option>
                        <option>Antartica</option>
                        <option>Isla de pascua</option>
                        <option>Metropolitana</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <label for="inputState" class="form-label">Comuna*</label>
                    <input type="text" class="form-control" id="inputCity">
                </div>
                <div class="col-12">
                    <label for="inputDescription" class="form-label">Descripcion</label>
                    <textarea id="inputDescription" class="form-control" rows="3"></textarea>
                </div>
            </div>
            </form>

            <form class="row g-3" v-if="progress==75">
                <div class="row">
                    <fieldset class="row mb-3">
                        <div class="col-md-6">
                            <label for="inputusername4" class="form-label">Tipo de publicacion</label>
                            <select id="inputRegion" class="form-select">
                                <option selected>...</option>
                                <option>Venta</option>
                                <option>Arriendo</option>
                            </select>
                        </div>
                        <div class="check-pay">
                        <legend class="col-form-label">Tipo de pago</legend>
                            <div class="col-sm-10">
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="gridCheck1">
                                    <label class="form-check-label" for="gridCheck1">
                                    Transferencia
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="gridCheck1">
                                    <label class="form-check-label" for="gridCheck1">
                                    Efectivo
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input" type="checkbox" id="gridCheck1">
                                    <label class="form-check-label" for="gridCheck1">
                                    Debito o credito
                                    </label>
                                </div>
                            </div>
                        </div>
                    <div class="final-instructions">
                        <div class="col-12">
                            <label for="inputDescription" class="form-label">Indicaciones</label>
                            <textarea id="inputDescription" class="form-control" rows="3"></textarea>
                        </div>
                    </div>
                    </fieldset>
                </div>
            </form>

            <form class="row g-3" v-if="progress==100">
                <div class="card-container">
                    <div class="card">

                        <div class="card-content">
                            <h3 class="letter">Tier 1</h3>
                            <p class="letter">Descripción de la verificacion: "Texto de ejemplo"</p>
                            <p class="letter">Valor: "Texto de ejemplo"</p>
                            <router-link to="/buy">
                                <button class="btn btn-primary">Comprar</button>
                            </router-link>
                        </div>
                    </div>
                    <div class="card">

                        <div class="card-content">
                            <h3 class="letter">Tier 2</h3>
                            <p class="letter">Descripción de la verificacion: "Texto de ejemplo"</p>
                            <p class="letter">Valor: "Texto de ejemplo"</p>
                            <router-link to="/buy">
                                <button class="btn btn-primary">Comprar</button>
                            </router-link>
                        </div>
                    </div>
                    <div class="card">
                        <div class="card-content">
                            <h3 class="letter">Tier 3</h3>
                            <p class="letter">Descripción de la verificacion: "Texto de ejemplo"</p>
                            <p class="letter">Valor: "Texto de ejemplo"</p>
                            <router-link to="/buy">
                                <button class="btn btn-primary">Comprar</button>
                            </router-link>
                        </div>
                    </div>
                </div>
            </form>
            <div class="final-publish">
                <router-link to="/">
                    <button class="btn btn-primary" type="submit" :disabled="!isPublishEnabled">Publicar</button>
                </router-link>
            </div>
        </div>
    </div>

</template>
<script>
    // Inicializa mostrando la sección de Datos Personales
    export default{

        data(){

            return{

                activeItem: 1,
                progress: 25,
                houseType: false,
                apartmentType: false,
                terrainType: false,
                imageSrc: null,
            }
        },
        computed: {
            progressWidth() {
                return `${this.progress}%`;
            },
            isPublishEnabled() {
                return this.progress === 100;
            }
        },
        methods:{

            setActive(index) {
                this.activeItem = index;
            },
            prevSection(){
                if (this.activeItem > 1) {
                    this.activeItem--;
                    this.progress = Math.max(this.progress - 25, 0);
                }
            },
            nextSection(){
                if (this.activeItem < 4) {
                    this.activeItem++;
                    this.progress = Math.min(this.progress + 25, 100);
                }
            },
            previewImage(event) {
                const file = event.target.files[0];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = (e) => {
                    this.imageSrc = e.target.result;
                };
                    reader.readAsDataURL(file);
                } else {
                    this.imageSrc = null;
                }
            }
            
        }
    }

</script>


<style>

.main{

    display: flex;
    justify-content: center;
    align-items: center;
    background: linear-gradient(45deg, #ded1b6, #ded1b6, #6ca19e, #6d997a);
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    height: auto; /* Ajusta la altura según el contenido */
    min-height: 100vh;
}

.content{

    box-sizing: border-box;
    align-items: left;
    background-color: #f8f6f6f3;
    flex-direction: column;
    min-width: 300px;
    max-width: 300px;
    width: 1%;
    margin: 0px 10px 0px 10px;
    min-height: 500px;
    max-height: 550px;
    height: 100%;
    border-radius: 10px;
    display: flex; /* Usa flexbox para alinear elementos */
    justify-content: flex-start; /* Alinea los elementos al inicio (izquierda) */
}

.content2{

    box-sizing: border-box;
    align-items: left;
    background-color: #f8f6f6f3;
    flex-direction: column;
    min-width: 300px;
    max-width: 1000px;
    width: 100%;
    margin: 0px 10px 0px 10px;
    min-height: 500px;
    max-height: 550px;
    height: 100%;
    border-radius: 10px;
    display: flex; /* Usa flexbox para alinear elementos */
    justify-content: flex-start; /* Alinea los elementos al inicio (izquierda) */
}

.colum-content1 {
    width: 300px; /* Ajusta el ancho según tus necesidades */
    height: 300px;
    /* Opcional: Añade un fondo o bordes para destacar el contenedor */
    padding: 0; /* Elimina el padding si es necesario */
    margin: 0; /* Elimina el margen si es necesario */
}

/* Estilo para la lista de navegación */
.nav {
    width: 100%; /* Asegura que la lista use el 100% del ancho del contenedor */
    padding: 0; /* Elimina el padding */
    margin: 0; /* Elimina el margen */
    list-style: none; /* Elimina los puntos de lista */
}

/* Estilo para los ítems de navegación */
.nav-item {
    width: 100%; /* Asegura que cada ítem use el 100% del ancho de la lista */
}

/* Estilo para los enlaces de navegación */
.nav-link {
    display: block; /* Hace que el enlace ocupe el bloque completo del ítem */
    width: 100%; /* Asegura que el enlace use el 100% del ancho del ítem */
    padding: 10px; /* Añade espaciado interno opcional */
    text-align: left; /* Alinea el texto a la izquierda */
    color: #42282c; /* Color de texto opcional */
    text-decoration: none; /* Elimina el subrayado por defecto */
    border-radius: 10px;
}

/* Estilo para el enlace activo */
.nav-link.active {
    background-color: #6d997a; /* Color de fondo del enlace activo */
    color: white; /* Color del texto del enlace activo */
    width: 100%;
}

.btn-primary{
    
    padding-bottom: 10px;
    width: 100%;
}

form.d-flex {
    margin-top: auto; /* Empuja el botón hacia el final del contenedor */
}

form {
    width: 100%; /* Asegura que el formulario ocupe todo el ancho del contenedor */
}

/* Estilo para los elementos del formulario */
.form-label {
    margin-bottom: 0.5rem; /* Espaciado debajo de las etiquetas del formulario */
}

.form-control, .form-select {
    margin-left: 10px;
    margin-bottom: 1rem; /* Espaciado debajo de los campos del formulario */
}

/* Asegura que el formulario use espacio disponible en el contenedor */
.row.g-3 {
    margin-top: 10px;
    display: flex;
    flex-wrap: wrap;
}

.row.g-3 .col-md-6, 
.row.g-3 .col-md-4, 
.row.g-3 .col-md-2 {
    box-sizing: border-box; /* Incluye el padding y el borde en el tamaño total del elemento */
}

/* Estilo opcional para el checkbox */
.form-check {
    display: flex;
    align-items: center;
}
.form-label{
    margin-left: 10px;
    color: black;
}
header {
    position: absolute; /* Posiciona el encabezado en relación al contenedor más cercano con posición no estática */
    top: 0; /* Alinea al borde superior del contenedor */
    left: 0; /* Alinea al borde izquierdo del contenedor */
    width: 100%; /* Asegura que el encabezado ocupe todo el ancho del contenedor */
    z-index: 1000; /* Asegura que el encabezado esté por encima de otros elementos */
    padding: 10px; /* Ajusta el padding según sea necesario */
    box-sizing: border-box; /* Incluye padding en el ancho total del contenedor */
    margin-left: 25px;
}

.header-section {
    display: flex; /* Usa flexbox para alinear el contenido dentro del header */
    justify-content: flex-start; /* Alinea el contenido al inicio del contenedor */
}

.page-title {
    color: black;
    margin: 0; /* Elimina el margen predeterminado */
    font-size: 2rem; /* Ajusta el tamaño de la fuente según sea necesario */
}

a {
    text-decoration: none; /* Elimina el subrayado de todos los enlaces */
}

/* Si quieres eliminar el subrayado solo de los enlaces en el nav */
.nav-link {
    text-decoration: none; /* Elimina el subrayado solo de los enlaces en la barra de navegación */
}

.form-instruction{

    color: black;
    margin-left: 315px;
}

.btn-primary:disabled {
    background-color: #6ca19e; /* Color de fondo cuando está desactivado */
    border-color: #c0c0c0; /* Color del borde cuando está desactivado */
    color: #6c757d; /* Color del texto cuando está desactivado */
    cursor: not-allowed; /* Cambia el cursor para indicar que no se puede hacer clic */
}
.progress-bar{
    transition: width 0.8s ease, background-color 0.3s ease;
    background-color: #42282c;
}
.progress-bar-full{
    
    background-color: #6ca19e;
}
.form-check-label{

    color:black;
}
.col-form-label{

    color: black;
}

.image-preview {
    margin-top: 10px;
}

.img-thumbnail {
    max-width: 200px;
    height: auto;
    border: 1px solid #ddd;
    padding: 5px;
}

.card-container {
    margin-top: 70px;
    display: flex;
    justify-content: space-between; /* Espacio igual entre las cartas */
    flex-wrap: wrap; /* Permite que las cartas se muevan a la siguiente línea si no caben en una sola línea */
}

.card {
    color: black;
    flex: 1 1 30%; /* Cada carta ocupará aproximadamente el 30% del ancho del contenedor, y se ajustará según sea necesario */
    margin: 10px; /* Espacio alrededor de cada carta */
    border: 1px solid #ddd; /* Borde alrededor de las cartas */
    border-radius: 8px; /* Bordes redondeados */
    overflow: hidden; /* Asegura que el contenido no se desborde fuera del borde de la carta */
}

.card img {
    width: 100%; /* Asegura que la imagen ocupe todo el ancho de la carta */
    height: auto; /* Mantiene la proporción de la imagen */
}

.card-content {
    color: black;
    padding: 15px; /* Espacio interno dentro de la carta */
}

.check-pay{

    margin-left: 10px;
}

.final-instructions{

    margin-top: 20px;

}
.final-publish {
    margin-top: auto; /* Empuja el botón al final del contenedor */
}
</style>