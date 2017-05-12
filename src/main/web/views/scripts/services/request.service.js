/**
 * Created by Ника Тихоновец on 01.05.2017.
 */

nseApp.service('RequestService', RequestService);

function RequestService($http) {
    this.getAll = function (url, callback) {
        $http({
            method: 'GET',
            url: url
        }).then(function successCallback(response) {
            if (response.status == 200){
                callback(null, response.data);
            }
        }, function errorCallback(response) {
            callback(response.data);
        })
    };

    this.getItemById = function (url,callback) {
        $http({
            method: 'GET',
            url: url
        }).then(function successCallback(response) {
            if (response.status == 200){
                callback(null, response.data);
            }
        }, function errorCallback(response) {
            callback(response.data);
        })
    };

    this.deleteItem = function (url, callback) {
        $http({
            method: 'DELETE',
            url: url
        }).then(function successCallback(response) {
            if (response.status == 200)
            {
                callback(null, response.status)
            }
        }, function errorCallback(response) {
            callback(response.data)
        });
    };

    this.editItem = function (url, data, callback) {
        $http({
            method: 'PUT',
            url: url,
            data: data
        }).then(function successCallback(response) {
            if (response.status == 200){
                callback(null, response.status)
            }
        }, function errorCallback(response) {
            callback({code: "Произошла ошибка редактирования. Проверьте введенные данные! И попробуйте снова."});
        });
    };
    
    this.addItem = function (url, data, callback) {
        $http({
            method: 'POST',
            url: url,
            data: data
        }).then(function successCallback(response) {
            if (response.status == 200){
                callback(null, response.status)
            }
        }, function errorCallback(response) {
            callback({code: "Произошла ошибка добавления. Проверьте введенные данные! И попробуйте снова."});
        });
    }
}