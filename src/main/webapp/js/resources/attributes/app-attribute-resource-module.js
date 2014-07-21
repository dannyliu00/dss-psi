(function() {
    var appAttributes = sellInNamespace('sellIn.resources.attribute');

    angular.module('sellIn.resources.attribute', ['ngResource'])
        .service('appAttributeResource', appAttributes.AppAttributeResource)
        .constant('attributeUrl', '/dss-psi/webapi/attributes/');

})();