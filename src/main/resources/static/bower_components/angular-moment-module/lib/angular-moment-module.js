/*global angular */
(function () {
    'use strict';

    angular
        .module('angular-moment-module', [])
        .factory('moment', function ($window) {
            return $window.moment;
        });
})();