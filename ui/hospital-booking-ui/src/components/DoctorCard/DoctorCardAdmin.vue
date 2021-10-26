<template>
  <div>
    <v-card
        class="mx-auto my-12"
        max-width="274"
    >
      <v-card-title>
        <div  style="margin: auto">
         {{ doctor.name }}
        </div>
      </v-card-title>
      <v-img
          height="150"
          :src="doctor.image"
      ></v-img>
      <v-card-title>
        {{ doctor.about }}
      </v-card-title>
      <v-card-actions v-if="edit==true">
        <v-icon style="cursor: pointer" @click="removeDoctors()">
          {{ icons.mdiDelete }}
        </v-icon>
        <v-icon style="cursor: pointer" @click="updateDoctorDialog=true">
          {{ icons.mdiHumanEdit }}
        </v-icon>
      </v-card-actions>
    </v-card>



      <v-row justify="center">
        <v-dialog
            v-model="updateDoctorDialog"
            max-width="290"
        >
          <v-card>
            <v-card-title class="text-h5">
              <div style="margin: auto">تعديل طبيب</div>
            </v-card-title>
            <v-card-text>

              <v-row no-gutters justify="center" align="center">
                <v-col>
                  <v-file-input
                      v-model="file"
                      style="width: 300px"
                      show-size
                      label="اختار الصور"
                      accept="image/*"
                      @change="selectImage"
                  ></v-file-input>
                </v-col>
                <v-col cols="4" class="pl-2">
                  <div v-if="progress">
                    <div>
                      <v-progress-linear
                          v-model="progress"
                          color="light-blue"
                          height="25"
                          reactive
                      >
                        <strong>{{ progress }} %</strong>
                      </v-progress-linear>
                    </div>
                  </div>

                </v-col>
              </v-row>

              <v-text-field
                  style="margin-top: 20px"
                  label="اسم الطبيب"
                  required
                  v-model="doctor.name"
                  hide-details="auto"
              ></v-text-field>

              <v-textarea
                  style="margin-top: 20px"
                  outlined
                  v-model="doctor.about"
                  label="معلومات عن الطبيب"
                  required
              ></v-textarea>
              <v-select
                  item-value="id"
                  item-text="section.name"
                  v-model="doctor.hospitalSectionId2"
                  :items="allHospitalSection"
                  label="القسم"
              ></v-select>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="green darken-1"
                  text
                  @click="updateDoctor()"

              >
                حفظ
              </v-btn>

            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>





  </div>
</template>


<script>


import {mdiDelete, mdiHumanEdit} from "@mdi/js";
import DoctorService from '../../service/DoctorService';
import HospitalSectionService from '../../service/HospitalSectionService';
import UploadFilesService from '../../service/UploadFilesService';






export default {
  props: ['doctor','hospitalId','edit'],
  data: () => {
    return {
       ile: null,
      currentImage: undefined,
      previewImage: undefined,
      progress: 0,
      selectHosptialSectionId:0,
      updateDoctorDialog: false,
      allHospitalSection:[],
      icons: {
        mdiDelete,
        mdiHumanEdit
      },
    }

  },
  methods: {

     upload() {
      if (!this.currentImage) {
        this.message = "Please select an Image!";
        return;
      }
      this.progress = 0;
      UploadFilesService.upload(this.currentImage, (event) => {
        this.progress = Math.round((100 * event.loaded) / event.total);
      })
          .then((response) => {
            this.coverImage = response.data.name;
            this.doctor.image= this.coverImage;
         });
    },

    selectImage(image) {
      this.currentImage = image;
      this.previewImage = URL.createObjectURL(this.currentImage);
      this.progress = 0;
      this.message = "";
      this.upload();
    },
    updateDoctor(){
        DoctorService.updateDoctor(this.doctor).then(resp=>{

          if(resp.status===200)
          location.reload();


        })


    },
    removeDoctors() {

        DoctorService.removeDoctors(this.doctor.id).then((resp)=> {

            if(resp.status===200){
              location.reload();
            }

        })
    }
  },
  mounted() {


console.log('section======')
   HospitalSectionService.findAllByHospitalId(this.hospitalId).then(resp => {

console.log('section======')
console.log(resp.data)
      if (resp.status == 200)
        this.allHospitalSection = resp.data;
    });
  }
};
</script>
