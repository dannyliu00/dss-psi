(function () {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    function ProfileUILinker(tElement, tAttrs) {
        var basePath = 'js/directives/profiles/';
        var type = tAttrs.profile.type;
        var pathSuffix = '-template.html';

        return basePath + type + pathSuffix;
    }

    dealerProfiles.ProfileUILinker = ProfileUILinker;
})();