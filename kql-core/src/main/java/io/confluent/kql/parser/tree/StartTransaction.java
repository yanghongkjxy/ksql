/**
 * Copyright 2017 Confluent Inc.
 **/
package io.confluent.kql.parser.tree;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public final class StartTransaction
    extends Statement {

  private final List<TransactionMode> transactionModes;

  public StartTransaction(List<TransactionMode> transactionModes) {
    this(Optional.empty(), transactionModes);
  }

  public StartTransaction(NodeLocation location, List<TransactionMode> transactionModes) {
    this(Optional.of(location), transactionModes);
  }

  private StartTransaction(Optional<NodeLocation> location,
                           List<TransactionMode> transactionModes) {
    super(location);
    this.transactionModes =
        ImmutableList.copyOf(requireNonNull(transactionModes, "transactionModes is null"));
  }

  public List<TransactionMode> getTransactionModes() {
    return transactionModes;
  }

  @Override
  public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
    return visitor.visitStartTransaction(this, context);
  }

  @Override
  public int hashCode() {
    return Objects.hash(transactionModes);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    final StartTransaction other = (StartTransaction) obj;
    return Objects.equals(this.transactionModes, other.transactionModes);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("transactionModes", transactionModes)
        .toString();
  }
}