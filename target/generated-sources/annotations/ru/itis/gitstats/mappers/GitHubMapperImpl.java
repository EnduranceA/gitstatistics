package ru.itis.gitstats.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.itis.gitstats.dto.DeveloperProductivityDto;
import ru.itis.gitstats.dto.github.CharacteristicsDto;
import ru.itis.gitstats.dto.github.CharacteristicsDto.CharacteristicsDtoBuilder;
import ru.itis.gitstats.dto.github.UserDto;
import ru.itis.gitstats.dto.github.pullrequest.RepoDto;
import ru.itis.gitstats.dto.github.statistics.ContributorCommitActivityShortDto;
import ru.itis.gitstats.models.Characteristics;
import ru.itis.gitstats.models.Characteristics.CharacteristicsBuilder;
import ru.itis.gitstats.models.Repo;
import ru.itis.gitstats.models.Repo.RepoBuilder;
import ru.itis.gitstats.models.User;
import ru.itis.gitstats.models.User.UserBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-16T09:18:23+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class GitHubMapperImpl implements GitHubMapper {

    @Override
    public Repo repoDtoToModel(RepoDto repoDto) {
        if ( repoDto == null ) {
            return null;
        }

        RepoBuilder repo = Repo.builder();

        repo.id( repoDto.getId() );
        repo.owner( userDtoToModel( repoDto.getOwner() ) );
        repo.name( repoDto.getName() );
        repo.stargazersCount( repoDto.getStargazersCount() );
        repo.isTemplate( repoDto.getIsTemplate() );
        repo.pushedAt( repoDto.getPushedAt() );
        repo.subscriptionUrl( repoDto.getSubscriptionUrl() );
        repo.branchesUrl( repoDto.getBranchesUrl() );
        repo.issueCommentUrl( repoDto.getIssueCommentUrl() );
        repo.allowRebaseMerge( repoDto.getAllowRebaseMerge() );
        repo.labelsUrl( repoDto.getLabelsUrl() );
        repo.subscribersUrl( repoDto.getSubscribersUrl() );
        repo.tempCloneToken( repoDto.getTempCloneToken() );
        repo.releasesUrl( repoDto.getReleasesUrl() );
        repo.svnUrl( repoDto.getSvnUrl() );
        repo.subscribersCount( repoDto.getSubscribersCount() );
        repo.forks( repoDto.getForks() );
        repo.archiveUrl( repoDto.getArchiveUrl() );
        repo.allowMergeCommit( repoDto.getAllowMergeCommit() );
        repo.gitRefsUrl( repoDto.getGitRefsUrl() );
        repo.forksUrl( repoDto.getForksUrl() );
        repo.visibility( repoDto.getVisibility() );
        repo.statusesUrl( repoDto.getStatusesUrl() );
        repo.networkCount( repoDto.getNetworkCount() );
        repo.sshUrl( repoDto.getSshUrl() );
        repo.fullName( repoDto.getFullName() );
        repo.size( repoDto.getSize() );
        repo.allowAutoMerge( repoDto.getAllowAutoMerge() );
        repo.languagesUrl( repoDto.getLanguagesUrl() );
        repo.htmlUrl( repoDto.getHtmlUrl() );
        repo.collaboratorsUrl( repoDto.getCollaboratorsUrl() );
        repo.cloneUrl( repoDto.getCloneUrl() );
        repo.pullsUrl( repoDto.getPullsUrl() );
        repo.defaultBranch( repoDto.getDefaultBranch() );
        repo.hooksUrl( repoDto.getHooksUrl() );
        repo.treesUrl( repoDto.getTreesUrl() );
        repo.tagsUrl( repoDto.getTagsUrl() );
        repo.jsonMemberPrivate( repoDto.getJsonMemberPrivate() );
        repo.contributorsUrl( repoDto.getContributorsUrl() );
        repo.hasDownloads( repoDto.getHasDownloads() );
        repo.notificationsUrl( repoDto.getNotificationsUrl() );
        repo.openIssuesCount( repoDto.getOpenIssuesCount() );
        repo.description( repoDto.getDescription() );
        repo.createdAt( repoDto.getCreatedAt() );
        repo.watchers( repoDto.getWatchers() );
        repo.deploymentsUrl( repoDto.getDeploymentsUrl() );
        repo.keysUrl( repoDto.getKeysUrl() );
        repo.hasProjects( repoDto.getHasProjects() );
        repo.archived( repoDto.getArchived() );
        repo.hasWiki( repoDto.getHasWiki() );
        repo.updatedAt( repoDto.getUpdatedAt() );
        repo.commentsUrl( repoDto.getCommentsUrl() );
        repo.stargazersUrl( repoDto.getStargazersUrl() );
        repo.disabled( repoDto.getDisabled() );
        repo.deleteBranchOnMerge( repoDto.getDeleteBranchOnMerge() );
        repo.gitUrl( repoDto.getGitUrl() );
        repo.hasPages( repoDto.getHasPages() );
        repo.allowSquashMerge( repoDto.getAllowSquashMerge() );
        repo.commitsUrl( repoDto.getCommitsUrl() );
        repo.compareUrl( repoDto.getCompareUrl() );
        repo.gitCommitsUrl( repoDto.getGitCommitsUrl() );
        repo.blobsUrl( repoDto.getBlobsUrl() );
        repo.gitTagsUrl( repoDto.getGitTagsUrl() );
        repo.mergesUrl( repoDto.getMergesUrl() );
        repo.downloadsUrl( repoDto.getDownloadsUrl() );
        repo.hasIssues( repoDto.getHasIssues() );
        repo.url( repoDto.getUrl() );
        repo.contentsUrl( repoDto.getContentsUrl() );
        repo.mirrorUrl( repoDto.getMirrorUrl() );
        repo.milestonesUrl( repoDto.getMilestonesUrl() );
        repo.teamsUrl( repoDto.getTeamsUrl() );
        repo.fork( repoDto.getFork() );
        repo.issuesUrl( repoDto.getIssuesUrl() );
        repo.eventsUrl( repoDto.getEventsUrl() );
        repo.issueEventsUrl( repoDto.getIssueEventsUrl() );
        repo.assigneesUrl( repoDto.getAssigneesUrl() );
        repo.openIssues( repoDto.getOpenIssues() );
        repo.watchersCount( repoDto.getWatchersCount() );
        repo.nodeId( repoDto.getNodeId() );
        repo.homepage( repoDto.getHomepage() );
        repo.forksCount( repoDto.getForksCount() );

        return repo.build();
    }

    @Override
    public RepoDto repoModelToDto(Repo repo) {
        if ( repo == null ) {
            return null;
        }

        RepoDto repoDto = new RepoDto();

        repoDto.setStargazersCount( repo.getStargazersCount() );
        repoDto.setIsTemplate( repo.getIsTemplate() );
        repoDto.setPushedAt( repo.getPushedAt() );
        repoDto.setSubscriptionUrl( repo.getSubscriptionUrl() );
        repoDto.setBranchesUrl( repo.getBranchesUrl() );
        repoDto.setIssueCommentUrl( repo.getIssueCommentUrl() );
        repoDto.setAllowRebaseMerge( repo.getAllowRebaseMerge() );
        repoDto.setLabelsUrl( repo.getLabelsUrl() );
        repoDto.setSubscribersUrl( repo.getSubscribersUrl() );
        repoDto.setTempCloneToken( repo.getTempCloneToken() );
        repoDto.setReleasesUrl( repo.getReleasesUrl() );
        repoDto.setSvnUrl( repo.getSvnUrl() );
        repoDto.setSubscribersCount( repo.getSubscribersCount() );
        repoDto.setId( repo.getId() );
        repoDto.setForks( repo.getForks() );
        repoDto.setArchiveUrl( repo.getArchiveUrl() );
        repoDto.setAllowMergeCommit( repo.getAllowMergeCommit() );
        repoDto.setGitRefsUrl( repo.getGitRefsUrl() );
        repoDto.setForksUrl( repo.getForksUrl() );
        repoDto.setVisibility( repo.getVisibility() );
        repoDto.setStatusesUrl( repo.getStatusesUrl() );
        repoDto.setNetworkCount( repo.getNetworkCount() );
        repoDto.setSshUrl( repo.getSshUrl() );
        repoDto.setFullName( repo.getFullName() );
        repoDto.setSize( repo.getSize() );
        repoDto.setAllowAutoMerge( repo.getAllowAutoMerge() );
        repoDto.setLanguagesUrl( repo.getLanguagesUrl() );
        repoDto.setHtmlUrl( repo.getHtmlUrl() );
        repoDto.setCollaboratorsUrl( repo.getCollaboratorsUrl() );
        repoDto.setCloneUrl( repo.getCloneUrl() );
        repoDto.setName( repo.getName() );
        repoDto.setPullsUrl( repo.getPullsUrl() );
        repoDto.setDefaultBranch( repo.getDefaultBranch() );
        repoDto.setHooksUrl( repo.getHooksUrl() );
        repoDto.setTreesUrl( repo.getTreesUrl() );
        repoDto.setTagsUrl( repo.getTagsUrl() );
        repoDto.setJsonMemberPrivate( repo.getJsonMemberPrivate() );
        repoDto.setContributorsUrl( repo.getContributorsUrl() );
        repoDto.setHasDownloads( repo.getHasDownloads() );
        repoDto.setNotificationsUrl( repo.getNotificationsUrl() );
        repoDto.setOpenIssuesCount( repo.getOpenIssuesCount() );
        repoDto.setDescription( repo.getDescription() );
        repoDto.setCreatedAt( repo.getCreatedAt() );
        repoDto.setWatchers( repo.getWatchers() );
        repoDto.setDeploymentsUrl( repo.getDeploymentsUrl() );
        repoDto.setKeysUrl( repo.getKeysUrl() );
        repoDto.setHasProjects( repo.getHasProjects() );
        repoDto.setArchived( repo.getArchived() );
        repoDto.setHasWiki( repo.getHasWiki() );
        repoDto.setUpdatedAt( repo.getUpdatedAt() );
        repoDto.setCommentsUrl( repo.getCommentsUrl() );
        repoDto.setStargazersUrl( repo.getStargazersUrl() );
        repoDto.setDisabled( repo.getDisabled() );
        repoDto.setDeleteBranchOnMerge( repo.getDeleteBranchOnMerge() );
        repoDto.setGitUrl( repo.getGitUrl() );
        repoDto.setHasPages( repo.getHasPages() );
        repoDto.setOwner( userModelToDto( repo.getOwner() ) );
        repoDto.setAllowSquashMerge( repo.getAllowSquashMerge() );
        repoDto.setCommitsUrl( repo.getCommitsUrl() );
        repoDto.setCompareUrl( repo.getCompareUrl() );
        repoDto.setGitCommitsUrl( repo.getGitCommitsUrl() );
        repoDto.setBlobsUrl( repo.getBlobsUrl() );
        repoDto.setGitTagsUrl( repo.getGitTagsUrl() );
        repoDto.setMergesUrl( repo.getMergesUrl() );
        repoDto.setDownloadsUrl( repo.getDownloadsUrl() );
        repoDto.setHasIssues( repo.getHasIssues() );
        repoDto.setUrl( repo.getUrl() );
        repoDto.setContentsUrl( repo.getContentsUrl() );
        repoDto.setMirrorUrl( repo.getMirrorUrl() );
        repoDto.setMilestonesUrl( repo.getMilestonesUrl() );
        repoDto.setTeamsUrl( repo.getTeamsUrl() );
        repoDto.setFork( repo.getFork() );
        repoDto.setIssuesUrl( repo.getIssuesUrl() );
        repoDto.setEventsUrl( repo.getEventsUrl() );
        repoDto.setIssueEventsUrl( repo.getIssueEventsUrl() );
        repoDto.setAssigneesUrl( repo.getAssigneesUrl() );
        repoDto.setOpenIssues( repo.getOpenIssues() );
        repoDto.setWatchersCount( repo.getWatchersCount() );
        repoDto.setNodeId( repo.getNodeId() );
        repoDto.setHomepage( repo.getHomepage() );
        repoDto.setForksCount( repo.getForksCount() );

        return repoDto;
    }

    @Override
    public List<RepoDto> repoModelsToDtos(List<Repo> repos) {
        if ( repos == null ) {
            return null;
        }

        List<RepoDto> list = new ArrayList<RepoDto>( repos.size() );
        for ( Repo repo : repos ) {
            list.add( repoModelToDto( repo ) );
        }

        return list;
    }

    @Override
    public List<Repo> repoDtosToModels(List<RepoDto> repoDtos) {
        if ( repoDtos == null ) {
            return null;
        }

        List<Repo> list = new ArrayList<Repo>( repoDtos.size() );
        for ( RepoDto repoDto : repoDtos ) {
            list.add( repoDtoToModel( repoDto ) );
        }

        return list;
    }

    @Override
    public UserDto userModelToDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setGistsUrl( user.getGistsUrl() );
        userDto.setReposUrl( user.getReposUrl() );
        userDto.setFollowingUrl( user.getFollowingUrl() );
        userDto.setStarredUrl( user.getStarredUrl() );
        userDto.setLogin( user.getLogin() );
        userDto.setFollowersUrl( user.getFollowersUrl() );
        userDto.setType( user.getType() );
        userDto.setUrl( user.getUrl() );
        userDto.setSubscriptionsUrl( user.getSubscriptionsUrl() );
        userDto.setReceivedEventsUrl( user.getReceivedEventsUrl() );
        userDto.setAvatarUrl( user.getAvatarUrl() );
        userDto.setEventsUrl( user.getEventsUrl() );
        userDto.setHtmlUrl( user.getHtmlUrl() );
        userDto.setSiteAdmin( user.getSiteAdmin() );
        userDto.setGitHubId( user.getGitHubId() );
        userDto.setGravatarId( user.getGravatarId() );
        userDto.setNodeId( user.getNodeId() );
        userDto.setOrganizationsUrl( user.getOrganizationsUrl() );

        return userDto;
    }

    @Override
    public User userDtoToModel(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDto.getId() );
        user.gitHubId( userDto.getGitHubId() );
        user.login( userDto.getLogin() );
        user.type( userDto.getType() );
        user.gistsUrl( userDto.getGistsUrl() );
        user.reposUrl( userDto.getReposUrl() );
        user.followingUrl( userDto.getFollowingUrl() );
        user.starredUrl( userDto.getStarredUrl() );
        user.followersUrl( userDto.getFollowersUrl() );
        user.url( userDto.getUrl() );
        user.subscriptionsUrl( userDto.getSubscriptionsUrl() );
        user.receivedEventsUrl( userDto.getReceivedEventsUrl() );
        user.avatarUrl( userDto.getAvatarUrl() );
        user.eventsUrl( userDto.getEventsUrl() );
        user.htmlUrl( userDto.getHtmlUrl() );
        user.siteAdmin( userDto.getSiteAdmin() );
        user.gravatarId( userDto.getGravatarId() );
        user.nodeId( userDto.getNodeId() );
        user.organizationsUrl( userDto.getOrganizationsUrl() );

        return user.build();
    }

    @Override
    public List<UserDto> userModelsToDto(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( users.size() );
        for ( User user : users ) {
            list.add( userModelToDto( user ) );
        }

        return list;
    }

    @Override
    public Characteristics characteristicsDtoToModel(CharacteristicsDto characteristicsDto) {
        if ( characteristicsDto == null ) {
            return null;
        }

        CharacteristicsBuilder characteristics = Characteristics.builder();

        characteristics.averageTimeEstimationPR( characteristicsDto.getAverageTimeEstimationPR() );
        characteristics.teamAtmosphere( characteristicsDto.getTeamAtmosphere() );
        characteristics.developerTurnoverPerYear( characteristicsDto.getDeveloperTurnoverPerYear() );
        Set<String> set = characteristicsDto.getImportantPackages();
        if ( set != null ) {
            characteristics.importantPackages( new HashSet<String>( set ) );
        }
        Set<String> set1 = characteristicsDto.getPackagesProneToBreakage();
        if ( set1 != null ) {
            characteristics.packagesProneToBreakage( new HashSet<String>( set1 ) );
        }
        Set<String> set2 = characteristicsDto.getStablePackages();
        if ( set2 != null ) {
            characteristics.stablePackages( new HashSet<String>( set2 ) );
        }
        Set<String> set3 = characteristicsDto.getUnstablePackages();
        if ( set3 != null ) {
            characteristics.unstablePackages( new HashSet<String>( set3 ) );
        }
        Set<String> set4 = characteristicsDto.getBalancedPackages();
        if ( set4 != null ) {
            characteristics.balancedPackages( new HashSet<String>( set4 ) );
        }
        List<ContributorCommitActivityShortDto> list = characteristicsDto.getContributorsActivity();
        if ( list != null ) {
            characteristics.contributorsActivity( new ArrayList<ContributorCommitActivityShortDto>( list ) );
        }
        List<DeveloperProductivityDto> list1 = characteristicsDto.getDevelopersPerformPoorly();
        if ( list1 != null ) {
            characteristics.developersPerformPoorly( new ArrayList<DeveloperProductivityDto>( list1 ) );
        }
        List<ContributorCommitActivityShortDto> list2 = characteristicsDto.getDevelopersRecycles();
        if ( list2 != null ) {
            characteristics.developersRecycles( new ArrayList<ContributorCommitActivityShortDto>( list2 ) );
        }

        return characteristics.build();
    }

    @Override
    public CharacteristicsDto characteristicsModelToDto(Characteristics characteristics) {
        if ( characteristics == null ) {
            return null;
        }

        CharacteristicsDtoBuilder characteristicsDto = CharacteristicsDto.builder();

        List<ContributorCommitActivityShortDto> list = characteristics.getContributorsActivity();
        if ( list != null ) {
            characteristicsDto.contributorsActivity( new ArrayList<ContributorCommitActivityShortDto>( list ) );
        }
        characteristicsDto.averageTimeEstimationPR( characteristics.getAverageTimeEstimationPR() );
        Set<String> set = characteristics.getImportantPackages();
        if ( set != null ) {
            characteristicsDto.importantPackages( new HashSet<String>( set ) );
        }
        Set<String> set1 = characteristics.getPackagesProneToBreakage();
        if ( set1 != null ) {
            characteristicsDto.packagesProneToBreakage( new HashSet<String>( set1 ) );
        }
        Set<String> set2 = characteristics.getStablePackages();
        if ( set2 != null ) {
            characteristicsDto.stablePackages( new HashSet<String>( set2 ) );
        }
        Set<String> set3 = characteristics.getUnstablePackages();
        if ( set3 != null ) {
            characteristicsDto.unstablePackages( new HashSet<String>( set3 ) );
        }
        Set<String> set4 = characteristics.getBalancedPackages();
        if ( set4 != null ) {
            characteristicsDto.balancedPackages( new HashSet<String>( set4 ) );
        }
        characteristicsDto.teamAtmosphere( characteristics.getTeamAtmosphere() );
        characteristicsDto.developerTurnoverPerYear( characteristics.getDeveloperTurnoverPerYear() );
        List<DeveloperProductivityDto> list1 = characteristics.getDevelopersPerformPoorly();
        if ( list1 != null ) {
            characteristicsDto.developersPerformPoorly( new ArrayList<DeveloperProductivityDto>( list1 ) );
        }
        List<ContributorCommitActivityShortDto> list2 = characteristics.getDevelopersRecycles();
        if ( list2 != null ) {
            characteristicsDto.developersRecycles( new ArrayList<ContributorCommitActivityShortDto>( list2 ) );
        }

        return characteristicsDto.build();
    }
}
