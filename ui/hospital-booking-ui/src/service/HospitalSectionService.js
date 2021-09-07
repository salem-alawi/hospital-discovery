import http from "../http-common";

class HospitalSectionService {

    removeHospitalSectionById(id){
        return http.delete('admin/hospitals/'+id+'/sections/'+id, );
    }

    findAllByHospitalId(id){
        return http.get('admin/hospitals/'+id+'/sections');
    }

}

export default new HospitalSectionService();