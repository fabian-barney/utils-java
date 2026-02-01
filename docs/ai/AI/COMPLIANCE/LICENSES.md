# LICENSES

This guidance is for commercial, closed-source use. It is not legal advice.
When in doubt, consult legal counsel.

## General Rules
- Only adopt dependencies with licenses compatible with closed-source
  commercial distribution.
- Prefer permissive licenses and well-governed stewardship.
- If a dependency is dual-licensed, select the compatible license explicitly.
- Record license decisions in an ADR when material.

## Attribution and Notice Obligations
- Many permissive licenses still require preserving copyright and license
  notices in distributions.
- When required, ship license texts and notices with the product (for example,
  `THIRD_PARTY_NOTICES.md` and/or a `licenses/` directory).
- If a license requires a NOTICE file (e.g., Apache-2.0), include it and keep it
  up to date.
- If the product UI or documentation must disclose third-party usage, ensure an
  appropriate "About" or credits section exists.
- Track attribution requirements in the dependency inventory so releases remain
  compliant.

## Generally Compatible (Permissive)
These licenses are typically business-friendly for closed-source use:
- Apache-2.0
- MIT
- BSD-2-Clause
- BSD-3-Clause
- ISC
- Zlib (zlib/libpng)
- BSL-1.0 (Boost Software License)
- CC0-1.0
- Unlicense

## Conditional / Requires Review
These may be compatible depending on usage and obligations. Legal review is
recommended before adopting them in closed-source products:
- MPL-2.0 (file-level copyleft)
- LGPL-2.1 / LGPL-3.0 (linking obligations)
- EPL-2.0 (weak copyleft)
- CDDL-1.0 (weak copyleft)

## Not Compatible (Avoid)
These licenses generally require open-source distribution or impose strong
copyleft obligations that conflict with closed-source products:
- GPL-2.0 / GPL-3.0
- AGPL-3.0
- SSPL-1.0
- Licenses with "Commons Clause" or non-commercial restrictions

## Verification
- Check the SPDX identifier in the dependency manifest.
- Confirm license text for custom or non-standard licenses.
- Ensure transitive dependencies are also compatible.
