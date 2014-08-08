(function () {
    var dealerProfile = sellInNamespace('sellIn.resources.dealerProfile');

    function DealerProfileResource($resource, profileUrl, profileSaveUrl, profileSubmitUrl, profileApproveWChangesUrl, profileApproveRequestedUrl, profileSubmitExceptionUrl, profileDsmSaveUrl, profileSendBackUrl, profileRsmSaveUrl, profileApproveCompliantUrl, profileApproveNonCompliantUrl, profileReturnToDsmUrl, profileNonDealerUrl) {
        this.resource = $resource(profileUrl, {}, {
            nonDealer: {method: 'GET', url: profileNonDealerUrl},
            save: {method: 'POST', url: profileSaveUrl},
            submit: {method: 'POST', url: profileSubmitUrl},
            dsmSave: {method: 'POST', url: profileDsmSaveUrl},
            sendBack: {method: 'POST', url: profileSendBackUrl},
            changed: {method: 'POST', url: profileApproveWChangesUrl},
            requested: {method: 'POST', url: profileApproveRequestedUrl},
            exception: {method: 'POST', url: profileSubmitExceptionUrl},
            rsmSave: {method: 'POST', url: profileRsmSaveUrl},
            returnDsm: {method: 'POST', url: profileReturnToDsmUrl},
            compliant: {method: 'POST', url: profileApproveCompliantUrl},
            nonCompliant: {method: 'POST', url: profileApproveNonCompliantUrl}
        });

    }

    DealerProfileResource.prototype.get = function (profile) {
        return this.resource.get(profile).$promise;
    };

    DealerProfileResource.prototype.nonDealer = function (profile) {
        return this.resource.nonDealer(profile).$promise;
    };

    DealerProfileResource.prototype.save = function (profile) {
        return this.resource.save(profile).$promise;
    };

    DealerProfileResource.prototype.submit = function (profile) {
        return this.resource.submit(profile).$promise;
    };

    DealerProfileResource.prototype.dsmSave = function (profile) {
        return this.resource.dsmSave(profile).$promise;
    };

    DealerProfileResource.prototype.sendBack = function (profile) {
        return this.resource.sendBack(profile).$promise;
    };

    DealerProfileResource.prototype.approveWChanges = function (profile) {
        return this.resource.changed(profile).$promise;
    };

    DealerProfileResource.prototype.approveRequested = function (profile) {
        return this.resource.requested(profile).$promise;
    };

    DealerProfileResource.prototype.submitException = function (profile) {
        return this.resource.exception(profile).$promise;
    };

    DealerProfileResource.prototype.rsmSave = function (profile) {
        return this.resource.rsmSave(profile).$promise;
    };

    DealerProfileResource.prototype.returnDsm = function (profile) {
        return this.resource.returnDsm(profile).$promise;
    };

    DealerProfileResource.prototype.compliant = function (profile) {
        return this.resource.compliant(profile).$promise;
    };

    DealerProfileResource.prototype.nonCompliant = function (profile) {
        return this.resource.nonCompliant(profile).$promise;
    };

    dealerProfile.DealerProfileResource = DealerProfileResource;
})();
