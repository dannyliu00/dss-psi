(function() {
    var appRole = sellInNamespace('sellIn.resources.role');

    function AppRoleResource($resource, roleUrl) {
        this.resource = $resource(roleUrl);
    }

    AppRoleResource.prototype.get = function() {
        return this.resource.get().$promise;
    };

    appRole.AppRoleResource = AppRoleResource;
})();