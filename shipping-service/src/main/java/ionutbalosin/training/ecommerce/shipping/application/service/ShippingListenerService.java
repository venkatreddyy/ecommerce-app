/**
 *  eCommerce Application
 *
 *  Copyright (c) 2022 - 2023 Ionut Balosin
 *  Website: www.ionutbalosin.com
 *  Twitter: @ionutbalosin / Mastodon: ionutbalosin@mastodon.socia
 *
 *
 *  MIT License
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */
package ionutbalosin.training.ecommerce.shipping.application.service;

import ionutbalosin.training.ecommerce.message.schema.shipping.ShippingStatus;
import ionutbalosin.training.ecommerce.message.schema.shipping.ShippingStatusUpdatedEvent;
import ionutbalosin.training.ecommerce.message.schema.shipping.ShippingTriggerCommand;
import ionutbalosin.training.ecommerce.shipping.application.event.builder.ShippingEventBuilder;
import ionutbalosin.training.ecommerce.shipping.application.event.mapper.ShippingMapper;
import ionutbalosin.training.ecommerce.shipping.domain.model.Shipping;
import ionutbalosin.training.ecommerce.shipping.domain.service.ShippingListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingListenerService implements ShippingListener {

  private final ShippingMapper shippingMapper;
  private final ShippingSenderService shippingSenderService;
  private final ShippingEventBuilder shippingEventBuilder;

  public ShippingListenerService(
      ShippingMapper shippingMapper,
      ShippingSenderService shippingSenderService,
      ShippingEventBuilder shippingEventBuilder) {
    this.shippingMapper = shippingMapper;
    this.shippingSenderService = shippingSenderService;
    this.shippingEventBuilder = shippingEventBuilder;
  }

  @Override
  public ShippingStatusUpdatedEvent process(ShippingTriggerCommand shippingCommand) {
    final Shipping shipping = shippingMapper.map(shippingCommand);
    ShippingStatus shippingStatus = shippingSenderService.triggerShipping(shipping);
    final ShippingStatusUpdatedEvent event =
        shippingEventBuilder.createEvent(shipping, shippingStatus);
    return event;
  }
}