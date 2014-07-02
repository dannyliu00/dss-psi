(function() {
    var rsmProfiles = sellInNamespace('sellIn.resources.rsmProfiles');

    function RsmProfilesResource($resource, rsmProfilesUrl) {
        this.resource = $resource(rsmProfilesUrl);
    }

    RsmProfilesResource.prototype.query = function(rsm) {
        return this.resource.query(rsm).$promise;
    };

    rsmProfiles.RsmProfilesResource = RsmProfilesResource;
})();