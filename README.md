# anonydog

A extremely simple Discord bot designed to facilitate anonymous messaging.

## Usage

You must create a `config.edn` file in the root directory (adjacent to README.md).
Its contents should appear as follows:

```clojure
{:token "DISCORD BOT TOKEN"
 :anon-nonce "INSERT ANY RANDOM STRING HERE"
 :playing "DM me and say !help"
 :anon-channel-id "CHANNEL ID WHERE DMs SHOULD BE FORWARDED"}
```

NOTE: The anon-nonce value must be kept secret.

Then, run the bot with `lein run`.

## License

Copyright © 2020 Nikola Bura

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
