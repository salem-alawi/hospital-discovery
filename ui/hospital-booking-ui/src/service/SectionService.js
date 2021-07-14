import http from "../http-common";

class SectionService {

    createNewSection(name, imageCover) {

        return http.post('admin/sections', {
            name: name,
            coverImage: imageCover
        });
    }
}

export default new SectionService();
