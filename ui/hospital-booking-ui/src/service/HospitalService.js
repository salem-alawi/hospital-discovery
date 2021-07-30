import http from "../http-common";

class HospitalService {

    createNewSection(createNewHospitalDto) {
        return http.post('admin/hospitals', createNewHospitalDto);
    }

    removeHospital(id) {
        return http.delete('admin/hospitals/' + id);
    }

    updateHospital(id, updateHospitalDto) {
        return http.put('admin/hospitals/' + id, updateHospitalDto);
    }

}

export default new HospitalService();
