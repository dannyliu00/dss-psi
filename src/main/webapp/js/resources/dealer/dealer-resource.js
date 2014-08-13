(function () {
    var dealer = sellInNamespace('sellIn.resources.dealer');

    function DealerResource($resource, dealerUrl, nonDealerUrl) {
        this.resource = $resource(dealerUrl, {}, {
            dealer: {method: 'GET', url: dealerUrl},
            nonDealer: {method: 'GET', url: nonDealerUrl}
        });
    }

    DealerResource.prototype.get = function (dealer) {
        return this.resource.dealer(dealer).$promise;
    };

    DealerResource.prototype.getNonDealer = function (dealer) {
        return this.resource.nonDealer(dealer).$promise;
    };

    dealer.DealerResource = DealerResource;
})();