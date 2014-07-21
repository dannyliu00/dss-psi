(function() {
    var appAttributes = sellInNamespace('sellIn.resources.attribute');

    function AppAttributeResource($resource, attributeUrl) {
        this.resource = $resource(attributeUrl, {
            ran: function() {
                // parameter added to URL to work around IE caching mechanism
                return new Date().getTime();
            }
        });
        
    }

    AppAttributeResource.prototype.get = function() {
        return this.resource.get().$promise;
    };

    appAttributes.AppAttributeResource = AppAttributeResource;
})();