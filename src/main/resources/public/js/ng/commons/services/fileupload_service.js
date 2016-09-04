angular.module('auctionhsa.commons')
	.service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl, params){
        var fd = new FormData();
        fd.append('file', file);
        fd.append('params',params);
        return $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        });
    }
}]);