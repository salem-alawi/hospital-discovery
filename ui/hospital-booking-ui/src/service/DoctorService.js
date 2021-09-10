import http from "../http-common";

class  DoctorService {

    createNewDoctor(doctorObject) {

        return http.post('admin/doctors', doctorObject);
    }

    findAllDoctorsByHospitalId(id){

    return http.get('admin/hospitals/'+id+'/doctors');

    }

    findDoctorCountBySectionId(id,hospitalSectionId){

        return http.get('admin/hospitals/'+id+'/sections/'+hospitalSectionId+'/doctor-count');

    }

    removeDoctors(id){
      return http.delete('/admin/doctors/'+id);
    }

    updateDoctor(doctor){
      return http.put('/admin/doctors/',doctor);
    }

    findDoctorCountBySectionIdPublic(hospitalSectionId){

        return http.get('/public/hospitals/'+hospitalSectionId+'/sections/'+hospitalSectionId+'/doctor-count');

    }

}

export default new DoctorService();
