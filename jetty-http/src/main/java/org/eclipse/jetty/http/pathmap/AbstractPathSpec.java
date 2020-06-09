//
// ========================================================================
// Copyright (c) 1995-2020 Mort Bay Consulting Pty Ltd and others.
//
// This program and the accompanying materials are made available under
// the terms of the Eclipse Public License 2.0 which is available at
// https://www.eclipse.org/legal/epl-2.0
//
// This Source Code may also be made available under the following
// Secondary Licenses when the conditions for such availability set
// forth in the Eclipse Public License, v. 2.0 are satisfied:
// the Apache License v2.0 which is available at
// https://www.apache.org/licenses/LICENSE-2.0
//
// SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
// ========================================================================
//

package org.eclipse.jetty.http.pathmap;

import java.util.Objects;

public abstract class AbstractPathSpec implements PathSpec
{
    @Override
    public int compareTo(PathSpec other)
    {
        // Grouping (increasing)
        int diff = getGroup().ordinal() - other.getGroup().ordinal();
        if (diff != 0)
            return diff;

        // Spec Length (decreasing)
        diff = other.getSpecLength() - getSpecLength();
        if (diff != 0)
            return diff;

        // Path Spec Name (alphabetical)
        return getDeclaration().compareTo(other.getDeclaration());
    }

    @Override
    public final boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        return compareTo((AbstractPathSpec)obj) == 0;
    }

    @Override
    public final int hashCode()
    {
        return Objects.hash(getDeclaration());
    }

    @Override
    public String toString()
    {
        return String.format("%s@%s{%s}", getClass().getSimpleName(), Integer.toHexString(hashCode()), getDeclaration());
    }
}