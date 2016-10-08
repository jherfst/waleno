# angular-moment-module

## Use Moment.js from an AngularJS Service, Controller, Directive or Filter

[![Build Status](https://travis-ci.org/patnolanireland/angular-moment-module.svg)](https://travis-ci.org/patnolanireland/angular-moment-module) ![Bower](https://img.shields.io/badge/bower-angular--moment--module-blue.svg)
---

##Overview

This module is a very simple wrapper around the global moment object exposed on the window.  It seeks to achieve
 the use case whereby injecting an Angular Service or Controller with $window may be less than desirable.

## Usage

1. Install

        bower install angular-moment-module

2. Ensure that moment.js and angular.js are included in your project followed by angular-moment-module

        <script src="bower_components/moment/moment.js></script>
        <script src="bower_components/angular/angular.js></script>
        <script src="bower_components/angular-underscore-module/lib/angular-underscore-module.js></script>

3. Load the angular-moment-module as a dependency in your module

        angular
            .module('app.modules', [
                'angular-moment-module']);

4. Make use of the moment service in your service or Controller

        angular
            .module('app.modules.registration')
            .controller('RegistrationCtrl', function(moment) {
                var today = moment();
            });

## Licence

angular-moment-module is licensed under the MIT Open Source license. For more information, see the LICENSE file in this
repository.

