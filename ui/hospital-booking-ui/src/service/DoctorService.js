import http from "../http-common";

class  DoctorService {

    createNewDoctor(doctorObject) {

        return http.post('admin/doctors', doctorObject);
    }

    findAllDoctorsByHospitalId(id){

    return http.get('admin/hospitals/'+id+'/doctors');

    }

}

export default new DoctorService();
