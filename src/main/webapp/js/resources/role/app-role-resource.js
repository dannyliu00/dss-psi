(function() {
    var appRole = sellInNamespace('sellIn.resources.role');

    function AppRoleResource($resource, roleUrl) {
        this.resource = $resource(roleUrl, {
            ran: function() {
                // parameter added to URL to work around IE caching mechanism
                return new Date().getTime();
            }
        });
    }

    AppRoleResource.prototype.get = function() {
        return this.resource.get().$promise;
    };

    appRole.AppRoleResource = AppRoleResource;
})();