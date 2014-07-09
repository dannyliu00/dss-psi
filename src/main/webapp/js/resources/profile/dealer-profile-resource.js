(function() {
    var dealerProfile = sellInNamespace('sellIn.resources.dealerProfile');

    function DealerProfileResource($resource, profileUrl, profileSaveUrl, profileSubmitUrl) {
        this.resource = $resource(profileUrl, {}, {
            save: {method: 'POST', url: profileSaveUrl},
            submit: {method: 'POST', url: profileSubmitUrl}
        });

    }

    DealerProfileResource.prototype.get = function(profile) {
        return this.resource.get(profile).$promise;
    };

    DealerProfileResource.prototype.save = function(profile) {
        return this.resource.save(profile).$promise;
    };

    DealerProfileResource.prototype.submit = function(profile) {
        return this.resource.submit(profile).$promise;
    };

    dealerProfile.DealerProfileResource = DealerProfileResource;
})();
