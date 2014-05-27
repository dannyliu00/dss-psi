(function() {
    var dealerProfiles = sellInNamespace('sellIn.resources.dealerProfiles');

    function DealerProfilesResource($resource, profilesUrl) {
        this.resource = $resource(profilesUrl);
    }

    DealerProfilesResource.prototype.query = function(profile) {
        return this.resource.query(profile).$promise;
    };

    dealerProfiles.DealerProfilesResource = DealerProfilesResource;
})();
