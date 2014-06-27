(function() {
    var dsmProfiles = sellInNamespace('sellIn.resources.dsmProfiles');

    function DsmProfilesResource($resource, dsmProfilesUrl) {
        this.resource = $resource(dsmProfilesUrl);
    }

    DsmProfilesResource.prototype.query = function(dsm) {
        return this.resource.query(dsm).$promise;
    };

    dsmProfiles.DsmProfilesResource = DsmProfilesResource;
})();
