<template>

    <div class="container main">

        <header>

            <div id="app">
                <logoComponent/>
            </div>

        </header>

        <div id="app">
            <mainComponent/>
        </div>
    </div>

</template>

<script>

    import mainComponent from '../components/mainComponent.vue';
    import logoComponent from '../components/logoComponent.vue';

    export default {
        components: {
            mainComponent,
            logoComponent
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

</style>