<template>
    <div class="HospitalDetailScreenAdmin">

        <v-btn @click="removeThisHospital">

            remove this hospital

        </v-btn>

        {{id}}
    </div>
</template>

<script>

    import HospitalService from "../../../../service/HospitalService";

    export default {
        props: ['id'],
        methods: {
            removeThisHospital() {
                HospitalService.removeHospital(this.id).then((resp) => {
                    if (resp.status == 200)
                        alert('remove success')
                    else
                        alert('error remove');
                })

            }

        }
    };
</script>
