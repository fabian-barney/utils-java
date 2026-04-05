# Releasing

`utils-java` publishes non-`SNAPSHOT` releases to Maven Central through the
Central Publisher Portal.

## Prerequisites

Before the release workflow can succeed:

- the `media.barney` namespace must be verified in the Central Publisher Portal
- a Central Portal user token must be generated for the publishing account
- a GPG key pair must exist and the public key must be published to a supported
  keyserver

## GitHub Secrets

Configure these repository secrets:

- `CENTRAL_USERNAME`: Central Publisher Portal user token username
- `CENTRAL_PASSWORD`: Central Publisher Portal user token password
- `CENTRAL_GPG_PRIVATE_KEY`: ASCII-armored private key used to sign artifacts
- `CENTRAL_GPG_PASSPHRASE`: passphrase for the private key

## Release Process

1. Make sure `main` contains the release-ready code.
2. Create an annotated tag using the release version prefixed with `v`, for
   example `v0.1.0`.
3. Push the tag to GitHub.
4. The `Release to Maven Central` workflow derives the Maven version from the
   tag, runs `./mvnw -Prelease deploy`, signs the artifacts, and publishes them
   via the Central Publisher Portal.

The project keeps `0.0.1-SNAPSHOT` as the default local development version and
overrides `revision` during tagged releases.

## Local Dry Run

You can validate the release profile locally without publishing by running:

```powershell
.\mvnw.cmd -B -ntp -Prelease -Drevision=0.1.0 -Dscm.tag=v0.1.0 -Dcentral.skipPublishing=true deploy
```

This still requires:

- a usable GPG private key in the local keyring

## Rollback Expectations

Maven Central is immutable. Once a version is published, do not reuse or
overwrite it.

If a release fails before the publish step completes, fix the workflow inputs or
configuration and publish a new version.

If a bad version is already visible on Maven Central, publish a newer version
with the fix and leave the original artifact in place.
