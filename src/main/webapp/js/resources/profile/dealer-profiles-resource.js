(function() {
    var dealerProfiles = sellInNamespace('sellIn.resources.dealerProfiles');

    function DealerProfilesResource($resource, profilesUrl) {
        this.resource = $resource(profilesUrl);
    }

    DealerProfilesResource.prototype.get = function(profile) {
        console.log(profile);
        return this.resource.get(profile).$promise;
    };

    dealerProfiles.DealerProfilesResource = DealerProfilesResource;
})();
