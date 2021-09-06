import http from "../http-common";

class HospitalSectionService {

    removeHospitalSectionById(id){
        return http.delete('admin/hospitals/'+id+'/sections/'+id, );
    }

}

export default new HospitalSectionService();